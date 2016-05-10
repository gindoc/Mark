package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.entity.FillBlankProblem;
import com.cwenhui.mark.entity.MulProblem;
import com.cwenhui.mark.entity.PaperDetail;
import com.cwenhui.mark.entity.SingleProblem;

/**
 * Created by cwenhui on 2016.02.23
 */
public class KnowledgePointFragment extends Fragment implements View.OnClickListener{
    private static final String PAPER_DETAIL = "paperDetail";
    private static final String TAG = "KnowledgePointFragment";
    private View mView;
//    private TextView tvType;                                        //试题类型
//    private TextView tvTitle;                                       //题目
//    private TextView tvOptionA, tvOptionB, tvOptionC, tvOptionD;    //每个选项的内容
//    private TextView tvA, tvB, tvC, tvD;                            //每个选项前的字母，用于显示选中项
    private LinearLayout llOptionA, llOptionB, llOptionC, llOptionD;//每个选项的布局，用于单击

    public static KnowledgePointFragment newInstance(PaperDetail paperDetail) {
        KnowledgePointFragment fragment = new KnowledgePointFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PAPER_DETAIL, paperDetail);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_knowledge_point, container, false);
        initData();
        initView();
        initEvent();
        return mView;
    }

    private void initData() {

    }

    private void initView() {
        llOptionA = (LinearLayout) mView.findViewById(R.id.ll_fragment_knowledge_point_a);
        llOptionB = (LinearLayout) mView.findViewById(R.id.ll_fragment_knowledge_point_b);
        llOptionC = (LinearLayout) mView.findViewById(R.id.ll_fragment_knowledge_point_c);
        llOptionD = (LinearLayout) mView.findViewById(R.id.ll_fragment_knowledge_point_d);
        setQuestion();

    }


    private void initEvent() {
        llOptionA.setOnClickListener(this);
        llOptionB.setOnClickListener(this);
        llOptionC.setOnClickListener(this);
        llOptionD.setOnClickListener(this);
    }

    /**
     * 设置试题类型
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
            Log.e(TAG, singleProblem.getSpTitle());
            tvType.setText("(单选题)");
            tvTitle.setText(singleProblem.getSpTitle());
            tvOptionA.setText(singleProblem.getSpAnswerA());
            tvOptionB.setText(singleProblem.getSpAnswerB());
            tvOptionC.setText(singleProblem.getSpAnswerC());
            tvOptionD.setText(singleProblem.getSpAnswerD());
        }else if (paperDetail.getMulProblem() != null) {
            MulProblem mulProblem = paperDetail.getMulProblem();
            Log.e(TAG, mulProblem.getMpTitle());
            tvType.setText("(多选题)");
            tvTitle.setText(mulProblem.getMpTitle());
            tvOptionA.setText(mulProblem.getMpAnswerA());
            tvOptionB.setText(mulProblem.getMpAnswerB());
            tvOptionC.setText(mulProblem.getMpAnswerC());
            tvOptionD.setText(mulProblem.getMpAnswerD());
        }else if (paperDetail.getFillBlankProblem() != null) {
            FillBlankProblem fillBlankProblem = paperDetail.getFillBlankProblem();
            Log.e(TAG, fillBlankProblem.getFbFrontTitle());
            tvType.setText("(填空题)");
            tvTitle.setText(fillBlankProblem.getFbFrontTitle() + "____" + fillBlankProblem.getFbBackTitle());
            ViewGroup viewGroup = (ViewGroup) mView.findViewById(R.id.ll_fragment_knowledge_point_container);
            viewGroup.removeView(llOptionA);
            viewGroup.removeView(llOptionB);
            viewGroup.removeView(llOptionC);
            viewGroup.removeView(llOptionD);
            EditText etFillBlank = new EditText(getActivity());
            etFillBlank.setId(Integer.MAX_VALUE-1);
            etFillBlank.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.transparent));
            viewGroup.addView(etFillBlank);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_fragment_knowledge_point_a:
                break;
            case R.id.ll_fragment_knowledge_point_b:
                break;
            case R.id.ll_fragment_knowledge_point_c:
                break;
            case R.id.ll_fragment_knowledge_point_d:
                break;

        }
    }
}
