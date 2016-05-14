package com.cwenhui.mark.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cwenhui.mark.R;
import com.cwenhui.mark.common.CommonRecyclerViewAdapter;
import com.cwenhui.mark.common.CommondRecyclerViewHolder;
import com.cwenhui.mark.common.MyEvent;
import com.cwenhui.mark.common.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * 答题页面
 * Created by Administrator on 2016/5/11.
 */
public class AnswerSheetFragment extends Fragment {
    public static final String EVENT_TYPE = "AnswerSheetFragment";
    private View mView;
    private RecyclerView recyclerView;
    private CommonRecyclerViewAdapter<String> adapter;
    private Map<Integer, String> answers = new HashMap<>();

//    public static final AnswerSheetFragment newInstance(List<PaperDetail> paperDetails) {
//        AnswerSheetFragment fragment = new AnswerSheetFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelableArrayList(EVENT_TYPE, (ArrayList<? extends Parcelable>) paperDetails);
//        return fragment;
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_answer_sheet, container, false);
        initData();
        initView();
        return mView;

    }

    private void initData() {
        List<String> answers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            answers.add(i + "");
        }
        adapter = new CommonRecyclerViewAdapter<String>(getActivity(),
                R.layout.item_fragment_answer_sheet, answers) {
            @Override
            public void convert(CommondRecyclerViewHolder holder, String s) {
                holder.setText(R.id.tv_item_fragment_answer_sheet, s);
                setUpItemEvent(holder);
            }
        };
        adapter.setClickListener(new OnAnswerClickListener());
    }

    private void initView() {
        EventBus.getDefault().register(this);
        recyclerView = (RecyclerView) mView.findViewById(R.id.rv_fragment_answer_sheet);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        recyclerView.setHasFixedSize(true);
    }

    /**
     * 接收各个页面的答题情况
     * @param myEvent 事件（包含各个页面的答题情况）
     */
    @Subscribe
    public void onEventMainThread(MyEvent myEvent) {
        if (myEvent.eventType == AnswerSheetFragment.EVENT_TYPE) {
            CompletionState completionState = (CompletionState) myEvent.eventData;
            setAnswersState(completionState);
        }
    }

    /**
     * 设置答题卡每个选项的状态
     * @param completionState 每个选项的状态，包含每个页面的索引和答案
     */
    public void setAnswersState(CompletionState completionState) {
        View view = recyclerView.getChildAt(completionState.index);
        TextView answer = (TextView) view.findViewById(R.id.tv_item_fragment_answer_sheet);
        Drawable drawable;
        if (!TextUtils.isEmpty(completionState.answer)) {           //如果填写了答案，填充背景色
            drawable = ContextCompat.getDrawable(getActivity(),
                    R.drawable.choice_question_option_selected);
        } else {                                                    //如果为填写答案，不填充背景色
            drawable = ContextCompat.getDrawable(getActivity(),
                    R.drawable.choice_question_option_normal);
        }
        answer.setBackground(drawable);
        answers.put(completionState.index, completionState.answer); //记录答题情况
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    class OnAnswerClickListener implements CommonRecyclerViewAdapter.onItemClickListener {

        @Override
        public void onItemClick(View view, int pos) {
            Toast.makeText(getActivity(), pos + "", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onItemLongClick(View view, int pos) {
        }
    }
}
