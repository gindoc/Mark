package com.cwenhui.mark.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.adapter.CommentRefreshRVAdapter;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.common.ScrollViewTouchListener;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.Comment;
import com.cwenhui.mark.entity.CompletedPractice;
import com.cwenhui.mark.presenter.CommentPresenter;
import com.cwenhui.mark.view.ICommentView;
import com.cwenhui.mark.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ParseFillBlankFragment extends Fragment implements ICommentView,
        SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    public static final String TAG = "ParseFillBlankFragment";
    public static final String INDEX = "index";
    public static final String TOTAL_QUESTION = "totalQuestion";
    private View view;
    private CompletedPractice completedPractice;                            //题目
    private CommonRecyclerViewAdapter<String> userAnswerAdapter;            //用户答案列表适配器
    private CommonRecyclerViewAdapter<String> recommendAnswerAdapter;       //参考答案列表适配器
    private SwipeRefreshLayout swipe;
    private ScrollView scrollView;
    private CommentPresenter presenter;
    private CommentRefreshRVAdapter commentAdapter;                         //评论列表（推荐解析）适配器
    private RecyclerView rvAnswerArea;                                      //用户答案列表
    private RecyclerView rvRecommendAnswer;                                 //参考答案列表

    public static ParseFillBlankFragment newInstance(int index, int totalQuesions, CompletedPractice
            completedPractice) {
        ParseFillBlankFragment fragment = new ParseFillBlankFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);                                        //题目索引
        bundle.putInt(TOTAL_QUESTION, totalQuesions);                       //总共试题数
        bundle.putParcelable(TAG, completedPractice);                       //题目
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_parse_fill_blank, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        presenter = new CommentPresenter(this);

        completedPractice = (CompletedPractice) getArguments().get(TAG);
        final String[] rmdAnswer = completedPractice.getPractice().getPraticeAnswer().split(Constant
                .SPLIT_OPTIONS);
        final String[] userAnswer = completedPractice.getUserAnswer().split(Constant.SPLIT_OPTIONS);

        List<String> rmdAnswers = new ArrayList<>();
        rmdAnswers.addAll(Arrays.asList(rmdAnswer));
        recommendAnswerAdapter = new CommonRecyclerViewAdapter<String>(getActivity(),
                R.layout.item_fragment_parse_fill_blank, rmdAnswers) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                char opt = (char) (holder.getLayoutPosition() + 65);                //根据pisition拿到选项
                holder.setText(R.id.tv_item_fragment_parse_fb_answer, s)
                        .setText(R.id.tv_item_fragment_parse_fb_No, opt + "");
            }
        };

        List<String> userAnswers = new ArrayList<>();
        userAnswers.addAll(Arrays.asList(userAnswer));
        userAnswerAdapter = new CommonRecyclerViewAdapter<String>(getActivity(), R.layout
                .item_fragment_parse_fill_blank, userAnswers) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                int pos = holder.getLayoutPosition();
                char opt = (char) (pos + 65);
                holder.setText(R.id.tv_item_fragment_parse_fb_No, opt + "")
                        .setText(R.id.tv_item_fragment_parse_fb_answer, s);
                //如果用户答案和参考答案不同，将下划线变红并添加X号图标
                if (!rmdAnswer[pos].equals(userAnswer[pos])) {
                    holder.getView(R.id.view_item_fragment_parse_fb_line)
                            .setBackgroundColor(Color.parseColor("#FF0000"));       //下划线变红
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setImageResource(R.drawable.fill_blank_error);
                    ViewGroup viewGroup = holder.getView(R.id.ll_item_fragment_parse_fb_container);
                    viewGroup.addView(imageView);                                   //添加X号图标
                }
            }
        };

    }

    private void initView() {
        setTitle();

        //设置总页数和当前页码
        TextView tvIndex = (TextView) view.findViewById(R.id.tv_fragment_parse_fb_completion);
        tvIndex.setText(getArguments().getInt(INDEX) + "/" + getArguments().getInt(TOTAL_QUESTION));

        TextView tvReview = (TextView) view.findViewById(R.id.tv_fragment_parse_fb_review); //查看解析
        tvReview.setOnClickListener(this);

        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fragment_parse_fb);
        swipe.setColorSchemeResources(R.color.swipeColor1, R.color.swipeColor2,
                R.color.swipeColor3, R.color.swipeColor4);
        swipe.setOnRefreshListener(this);

        scrollView = (ScrollView) view.findViewById(R.id.sv_fragment_parse_fb);

        rvAnswerArea = (RecyclerView) view.findViewById(R.id.rv_fragment_parse_fb_answer_area);
        rvAnswerArea.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rvAnswerArea.setAdapter(userAnswerAdapter);

        rvRecommendAnswer = (RecyclerView) view.findViewById(R.id
                .rv_fragment_parse_fb_reference);
        rvRecommendAnswer.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rvRecommendAnswer.setAdapter(recommendAnswerAdapter);
    }

    /**
     * 设置题目
     */
    private void setTitle() {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_fragment_parse_fb_title);
        SpannableString title = new SpannableString("(填空题)" + completedPractice.getPractice()
                .getPraticeTitle().replace(Constant.OPTIONS_SPECIAL_CHARS, "(  )"));
        int color = ContextCompat.getColor(getActivity(), R.color.themeColor);
        title.setSpan(new ForegroundColorSpan(color), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTitle.setText(title);
    }


    @Override
    public void initCommentList(List<Comment> comments) {
        view.findViewById(R.id.tv_fragment_parse_fb_review).setVisibility(View.GONE);
        view.findViewById(R.id.rv_fragment_parse_fb_recommend).setVisibility(View.VISIBLE);

        RecyclerView rvParsing = (RecyclerView) view.findViewById(R.id.rv_fragment_parse_fb_comment);
        rvParsing.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rvParsing.setVisibility(View.VISIBLE);

        commentAdapter = new CommentRefreshRVAdapter(getActivity(), R.layout.item_comment,
                R.layout.layout_footer_comment, comments);
        int[] location = new int[2];
        rvParsing.getLocationInWindow(location);                    //获取recyclerview在父容器中的坐标
        commentAdapter.setParentHeight(scrollView.getMeasuredHeight());

        //recyclerview在父容器（scrollview）中的位置=在父容器中的纵坐标+用户答案列表的高度+参考答案列表的高度
        //（因为用户答案列表和参考答案列表的高度是动态计算的，所以recyclerview在父容器中的纵坐标不准确，
        // 故要加上其他recyclerview的高度）
        commentAdapter.setY(location[1] + rvAnswerArea.getMeasuredHeight() + rvRecommendAnswer
                .getMeasuredHeight());
        rvParsing.setAdapter(commentAdapter);
        swipe.setRefreshing(false);
        //设置监听，判断是否滚动到了顶部或底部
        scrollView.setOnTouchListener(new ScrollViewTouchListener(scrollView, commentAdapter, swipe,
                presenter));
    }

    @Override
    public void loadMoreComments(List<Comment> comments) {
        commentAdapter.getmDatas().addAll(comments);
        commentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        presenter.reflesh(Constant.PULL_DOWN);
    }

    @Override
    public void onClick(View v) {
        ((TextView) v).setText("加载中...");
        presenter.initCommentList();
    }
}
