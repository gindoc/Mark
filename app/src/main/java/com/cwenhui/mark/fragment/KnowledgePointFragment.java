package com.cwenhui.mark.fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.MyEvent;
import com.cwenhui.mark.entity.FillBlankProblem;
import com.cwenhui.mark.entity.MulProblem;
import com.cwenhui.mark.entity.PaperDetail;
import com.cwenhui.mark.entity.SingleProblem;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by cwenhui on 2016.02.23
 */
public class KnowledgePointFragment extends Fragment implements View.OnClickListener/*, TextWatcher*/ {
    public static final String EVENT_TYPE = "KnowledgePointFragment";
    private static final String PAPER_DETAIL = "paperDetail";
    private static final String INDEX = "index";
    private static final String TAG = "KnowledgePointFragment";
    private View mView;
    private LinearLayout llOptionA, llOptionB, llOptionC, llOptionD;    //每个选项的布局，用于单击
    private TextView tvA, tvB, tvC, tvD;                                //每个选项前面的标记
    private EditText etFillBlank;

    public static KnowledgePointFragment newInstance(int index, PaperDetail paperDetail) {
        KnowledgePointFragment fragment = new KnowledgePointFragment();
        Bundle bundle = new Bundle();
//        bundle.putSerializable(PAPER_DETAIL, paperDetail);
        bundle.putParcelable(PAPER_DETAIL, paperDetail);
        bundle.putInt(INDEX, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_knowledge_point, container, false);
        initView();
        initEvent();
        return mView;
    }

    private void initView() {
        EventBus.getDefault().register(this);
        llOptionA = (LinearLayout) mView.findViewById(R.id.ll_fragment_knowledge_point_a);
        llOptionB = (LinearLayout) mView.findViewById(R.id.ll_fragment_knowledge_point_b);
        llOptionC = (LinearLayout) mView.findViewById(R.id.ll_fragment_knowledge_point_c);
        llOptionD = (LinearLayout) mView.findViewById(R.id.ll_fragment_knowledge_point_d);
        tvA = (TextView) llOptionA.findViewById(R.id.tv_fragment_knowledge_point_a);
        tvB = (TextView) llOptionB.findViewById(R.id.tv_fragment_knowledge_point_b);
        tvC = (TextView) llOptionC.findViewById(R.id.tv_fragment_knowledge_point_c);
        tvD = (TextView) llOptionD.findViewById(R.id.tv_fragment_knowledge_point_d);
        TextView tvIndex = (TextView) mView.findViewById(R.id.tv_fragment_knowledge_point_completion);
        tvIndex.setText(getArguments().getInt(INDEX) + 1 + "/10");
        setQuestion();

    }


    private void initEvent() {
        llOptionA.setOnClickListener(this);
        llOptionB.setOnClickListener(this);
        llOptionC.setOnClickListener(this);
        llOptionD.setOnClickListener(this);
    }

    /**
     * 设置试题
     */
    private void setQuestion() {
        PaperDetail paperDetail = (PaperDetail) getArguments().get(PAPER_DETAIL);
        TextView tvType = (TextView) mView.findViewById(R.id.tv_fragment_knowledge_point_type);
        TextView tvTitle, tvOptionA, tvOptionB, tvOptionC, tvOptionD;
        tvTitle = (TextView) mView.findViewById(R.id.tv_fragment_knowledge_point_title);
        tvOptionA = (TextView) mView.findViewById(R.id.tv_fragment_knowledge_point_a_opt);
        tvOptionB = (TextView) mView.findViewById(R.id.tv_fragment_knowledge_point_b_opt);
        tvOptionC = (TextView) mView.findViewById(R.id.tv_fragment_knowledge_point_c_opt);
        tvOptionD = (TextView) mView.findViewById(R.id.tv_fragment_knowledge_point_d_opt);
        if (paperDetail.getSingleProblem() != null) {
            SingleProblem singleProblem = paperDetail.getSingleProblem();
            tvType.setText("(单选题)");
            tvTitle.setText(singleProblem.getSpTitle());
            tvOptionA.setText(singleProblem.getSpAnswerA());
            tvOptionB.setText(singleProblem.getSpAnswerB());
            tvOptionC.setText(singleProblem.getSpAnswerC());
            tvOptionD.setText(singleProblem.getSpAnswerD());
        } else if (paperDetail.getMulProblem() != null) {
            MulProblem mulProblem = paperDetail.getMulProblem();
            tvType.setText("(多选题)");
            tvTitle.setText(mulProblem.getMpTitle());
            tvOptionA.setText(mulProblem.getMpAnswerA());
            tvOptionB.setText(mulProblem.getMpAnswerB());
            tvOptionC.setText(mulProblem.getMpAnswerC());
            tvOptionD.setText(mulProblem.getMpAnswerD());
        } else if (paperDetail.getFillBlankProblem() != null) {
            FillBlankProblem fillBlankProblem = paperDetail.getFillBlankProblem();
            tvType.setText("(填空题)");
            tvTitle.setText(fillBlankProblem.getFbFrontTitle() + "____" + fillBlankProblem.getFbBackTitle());
            addFillBlankArea();
        }

    }

    /**
     * 添加填空题答题区
     */
    private void addFillBlankArea() {
        final ViewGroup viewGroup = (ViewGroup) mView.findViewById(R.id.ll_fragment_knowledge_point_container);
        viewGroup.removeView(llOptionA);
        viewGroup.removeView(llOptionB);
        viewGroup.removeView(llOptionC);
        viewGroup.removeView(llOptionD);
        Context context = getActivity();
        TextView tvTag = new TextView(context);
        tvTag.setText("答题区");
        tvTag.setTextColor(ContextCompat.getColor(context, R.color.blackFontColor));
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.icon_profile_post);
        tvTag.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        tvTag.setCompoundDrawablePadding((int) context.getResources().getDimension(R.dimen.x10));
        tvTag.setTextSize(16);
        viewGroup.addView(tvTag);

        etFillBlank = new EditText(getActivity());
        etFillBlank.setId(Integer.MAX_VALUE - 1);
        etFillBlank.setBackground(null);
        drawable = ContextCompat.getDrawable(context, R.drawable.edittext_background);
        etFillBlank.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);
        etFillBlank.setHint("请输入答案");
        etFillBlank.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        etFillBlank.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        viewGroup.addView(etFillBlank);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_fragment_knowledge_point_a:
                sendCompletionState("a");
                setOptionState('a');
                break;
            case R.id.ll_fragment_knowledge_point_b:
                sendCompletionState("b");
                setOptionState('b');
                break;
            case R.id.ll_fragment_knowledge_point_c:
                sendCompletionState("c");
                setOptionState('c');
                break;
            case R.id.ll_fragment_knowledge_point_d:
                sendCompletionState("d");
                setOptionState('d');
                break;
        }
    }

    /**
     * 发送结果给AnswerSheetFragment,用于统计答题情况
     * @param answer 选项
     */
    private void sendCompletionState(String answer) {
        CompletionState state = new CompletionState();
        state.index = getArguments().getInt(INDEX);
        PaperDetail paperDetail = (PaperDetail) getArguments().get(PAPER_DETAIL);
//        if(paperDetail.get)
//        state.answer = answer;
        MyEvent myEvent = new MyEvent();
        myEvent.eventType = AnswerSheetFragment.EVENT_TYPE;
        myEvent.eventData = state;
        EventBus.getDefault().post(myEvent);
    }

    /**
     * 设置选项状态
     * @param answer 选项
     */
    private void setOptionState(char answer) {
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_normal);
        tvA.setBackground(drawable);
        tvB.setBackground(drawable);
        tvC.setBackground(drawable);
        tvD.setBackground(drawable);
        drawable = ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_selected);
        switch (answer) {
            case 'a':
                tvA.setBackground(drawable);
                break;
            case 'b':
                tvB.setBackground(drawable);
                break;
            case 'c':
                tvC.setBackground(drawable);
                break;
            case 'd':
                tvD.setBackground(drawable);
                break;
        }
    }

    /**
     * 接收KnowledgePointActivity发送过来的索引
     * 并在从填空题页面切换到其他页面时，发送此页面的填空题答题情况给AnswerSheetFragment
     * @param myEvent 事件（包含此时ViewPager所处位置）
     */
    @Subscribe
    public void onEventMainThread(MyEvent myEvent) {
        if (myEvent.eventType == KnowledgePointFragment.EVENT_TYPE) {   //只处理跟此Fragment相关的信息
            PaperDetail paperDetail = (PaperDetail) getArguments().get(PAPER_DETAIL);
            if (paperDetail.getFillBlankProblem() != null) {            //只处理填空题
                if ((Integer)myEvent.eventData != getArguments().getInt(INDEX)) {   //当页面从此填空题页面切换到另一页面时才处理
                    CompletionState completionState = new CompletionState();
                    completionState.index = getArguments().getInt(INDEX);
                    if (etFillBlank.getText().toString().equals(paperDetail.getFillBlankProblem().getFbAnswer())) {
                        completionState.isRight = true;
                    }else {
                        completionState.isRight = false;
                    }
//                    completionState.answer = etFillBlank.getText().toString();
                    MyEvent event = new MyEvent();
                    event.eventType = AnswerSheetFragment.EVENT_TYPE;   //发送给答题卡页面
                    event.eventData = completionState;                  //发送填空题答案
                    EventBus.getDefault().post(event);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}

/**
 * 答题情况
 */
class CompletionState {
    int index;          //所处页面索引
    String answer;      //所处页面答案
    boolean isRight;    //答案是否正确
}