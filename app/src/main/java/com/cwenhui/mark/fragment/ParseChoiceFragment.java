package com.cwenhui.mark.fragment;

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
public class ParseChoiceFragment extends Fragment implements View.OnLayoutChangeListener,
        View.OnClickListener, ICommentView, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "ParseChoiceFragment";
    public static final String INDEX = "index";
    public static final String TOTAL_QUESTION = "totalQuestion";
    private View view;
    private CompletedPractice completedPractice;                                //题目
    private SwipeRefreshLayout swipe;
    private ScrollView scrollView;
    private RecyclerView rvOptions;                                             //选项列表
    private CommonRecyclerViewAdapter<String> choiceAdapter;                    //选项列表适配器
    private CommentPresenter presenter;
    private CommentRefreshRVAdapter commentAdapter;                             //评论列表适配器

    public static ParseChoiceFragment newInstance(int index, int totalQues, CompletedPractice
            completedPractice) {
        ParseChoiceFragment fragment = new ParseChoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, completedPractice);
        bundle.putInt(INDEX, index);
        bundle.putInt(TOTAL_QUESTION, totalQues);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_parse_choice, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        presenter = new CommentPresenter(this);

        completedPractice = (CompletedPractice) getArguments().get(TAG);
        String[] options = completedPractice.getPractice().getPraticeOptions().split(Constant
                .SPLIT_OPTIONS);
        List<String> choices = new ArrayList<>();
        choices.addAll(Arrays.asList(options));
        choiceAdapter = new CommonRecyclerViewAdapter<String>(getActivity(),
                R.layout.item_fragment_choice_problem, choices) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                char opt = (char) (holder.getLayoutPosition() + 65);                   //根据pisition拿到选项
                holder.setText(R.id.tv_item_fragment_choice_problem_option, s)
                        .setText(R.id.tv_item_fragment_choice_problem, opt + "");
            }
        };
    }

    private void initView() {
        setTitle();

        //设置总页数和当前页码
        TextView tvIndex = (TextView) view.findViewById(R.id.tv_fragment_parse_choice_completion);
        tvIndex.setText(getArguments().getInt(INDEX) + "/" + getArguments().getInt(TOTAL_QUESTION));

        TextView tvReview = (TextView) view.findViewById(R.id.tv_fragment_parse_choice_review);
        tvReview.setOnClickListener(this);

        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fragment_parse_choice);
        swipe.setColorSchemeResources(R.color.swipeColor1, R.color.swipeColor2,
                R.color.swipeColor3, R.color.swipeColor4);
        swipe.setOnRefreshListener(this);

        scrollView = (ScrollView) view.findViewById(R.id.sv_fragment_parse_choice);

        rvOptions = (RecyclerView) view.findViewById(R.id.rv_fragment_parse_choice_problem);
        rvOptions.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rvOptions.setAdapter(choiceAdapter);
        rvOptions.addOnLayoutChangeListener(this);
    }

    /**
     * 设置题目
     */
    private void setTitle() {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_fragment_parse_choice_title);
        SpannableString title = null;
        switch (completedPractice.getPractice().getPraticeType()) {
            case Constant.SINGLE_PROBLEM:
                title = new SpannableString("(单选题) " + completedPractice.getPractice()
                        .getPraticeTitle());
                break;
            case Constant.MULTIPUL_PROBLEM:
                title = new SpannableString("(多选题) " + completedPractice.getPractice()
                        .getPraticeTitle());
                break;
        }
        int color = ContextCompat.getColor(getActivity(), R.color.themeColor);
        title.setSpan(new ForegroundColorSpan(color), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTitle.setText(title);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int
            oldTop, int oldRight, int oldBottom) {
        setOptionsState();
    }

    @Override
    public void onClick(View v) {
        ((TextView) v).setText("加载中...");
        presenter.initCommentList();
    }

    @Override
    public void initCommentList(List<Comment> comments) {
        view.findViewById(R.id.tv_fragment_parse_choice_review).setVisibility(View.GONE);
        view.findViewById(R.id.rv_fragment_parse_choice_recommend).setVisibility(View.VISIBLE);

        RecyclerView rvParsing = (RecyclerView) view.findViewById(R.id.rv_fragment_parse_choice_comment);
        rvParsing.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rvParsing.setVisibility(View.VISIBLE);

        commentAdapter = new CommentRefreshRVAdapter(getActivity(), R.layout.item_comment,
                R.layout.layout_footer_comment, comments);
        int[] location = new int[2];
        rvParsing.getLocationInWindow(location);                    //获取recyclerview在父容器中的坐标
        commentAdapter.setParentHeight(scrollView.getMeasuredHeight());

        //recyclerview在父容器（scrollview）中的位置=在父容器中的纵坐标+其他在父容器中的recyclerView的高度
        commentAdapter.setY(location[1] + rvOptions.getMeasuredHeight());
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

    /**
     * 设置选项状态（用户答错的为红色，正确答案为绿色）
     * 处理逻辑：先将用户选中的答案设置为红色（假设答错），然后如果是单选题或用户答对的情况下，将正确答案设置为绿色（此处
     * 处理了三种情况：1、如果用户答对了单选题，则界面只显示绿色；2、如果用户没答对单选题，则界面显示红色和绿色；
     * 3、如果用户答对了双选题，则界面只显示绿色），接下来处理双选题，用户只答对部分选项的情况，界面显示红、绿、半红半绿；
     * 最后处理用户完全答错的情况
     */
    private void setOptionsState() {
        int questionType = completedPractice.getPractice().getPraticeType();                    //题目类型
        String[] userAnswer = completedPractice.getUserAnswer().split(Constant.SPLIT_OPTIONS);  //用户答案
        for (int i = 0; i < userAnswer.length; i++) {
            int postion = userAnswer[i].charAt(0) - 65;
            View view = rvOptions.getChildAt(postion).findViewById(R.id
                    .tv_item_fragment_choice_problem);
            view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable
                    .choice_question_option_wrong));                                      //将用户答案设置成红色
        }

        String[] refAnswer = completedPractice.getPractice().getPraticeAnswer().split(Constant
                .SPLIT_OPTIONS);                                                          //参考答案
        for (int i = 0; i < refAnswer.length; i++) {
            int postion = refAnswer[i].charAt(0) - 65;
            View view = rvOptions.getChildAt(postion).findViewById(R.id
                    .tv_item_fragment_choice_problem);
            //单选题或用户答对的情况下，将参考答案设置成绿色
            if (questionType == Constant.SINGLE_PROBLEM || completedPractice.isRight()) {
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable
                        .choice_question_option_selected));
            } //用户答错，但不全错的情况下，将用户答对的某个选项设置从红绿相间的图片
            else if (completedPractice.getUserAnswer().contains(refAnswer[i])) {
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable
                        .parse_multiple));
            } //其他情况，多选题且用户全错的情况下，将参考答案设置成绿色
            else {
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable
                        .choice_question_option_selected));
            }
        }
    }
}
