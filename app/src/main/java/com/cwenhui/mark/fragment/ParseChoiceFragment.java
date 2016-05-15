package com.cwenhui.mark.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.configs.Constant;
import com.cwenhui.mark.entity.CompletedPractice;
import com.cwenhui.mark.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cwenhui on 2016.02.23
 */
public class ParseChoiceFragment extends Fragment implements View.OnLayoutChangeListener {
    public static final String TAG = "ParseChoiceFragment";
    private View view;
    private CompletedPractice completedPractice;
    private RecyclerView rvOptions;
    private CommonRecyclerViewAdapter<String> adapter;

    public static ParseChoiceFragment newInstance(CompletedPractice completedPractice) {
        ParseChoiceFragment fragment = new ParseChoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(TAG, completedPractice);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_parse_choice, container, false);
        initData();
        initView();
        return view;
    }

    private void initData() {
        completedPractice = (CompletedPractice) getArguments().get(TAG);
        String[] options = completedPractice.getPractice().getPraticeOptions().split(Constant.SPLIT_OPTIONS);
        List<String> choices = new ArrayList<>();
        choices.addAll(Arrays.asList(options));
        adapter = new CommonRecyclerViewAdapter<String>(getActivity(),
                R.layout.item_fragment_choice_problem, choices) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                char opt = (char) (holder.getLayoutPosition() + 65);
                holder.setText(R.id.tv_item_fragment_choice_problem_option, s)
                        .setText(R.id.tv_item_fragment_choice_problem, opt + "");
            }
        };
    }

    private void initView() {
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_fragment_parse_choice_title);
        tvTitle.setText(completedPractice.getPractice().getPraticeTitle());

        rvOptions = (RecyclerView) view.findViewById(R.id.rv_fragment_parse_choice_problem);
        rvOptions.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rvOptions.setAdapter(adapter);
        rvOptions.addOnLayoutChangeListener(this);
    }

    /**
     * 设置选项状态（用户答错的为红色，正确答案为绿色）
     */
    private void setOptionsState() {
        int questionType = completedPractice.getPractice().getPraticeType();
        String[] userAnswer = completedPractice.getUserAnswer().split(Constant.SPLIT_OPTIONS);
        for (int i = 0; i < userAnswer.length; i++) {
            int postion = userAnswer[i].charAt(0) - 65;
            View view = rvOptions.getChildAt(postion).findViewById(R.id.tv_item_fragment_choice_problem);
            view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_wrong));
        }
        String[] refAnswer = completedPractice.getPractice().getPraticeAnswer().split(Constant.SPLIT_OPTIONS);
        for (int i = 0; i < refAnswer.length; i++) {
            int postion = refAnswer[i].charAt(0) - 65;
            View view = rvOptions.getChildAt(postion).findViewById(R.id.tv_item_fragment_choice_problem);
            if (questionType == Constant.MULTIPUL_PROBLEM
                    && completedPractice.getUserAnswer().contains(refAnswer[i])) {
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.parse_multiple));
            } else {
                view.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.choice_question_option_selected));
            }
        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        setOptionsState();
    }
}
