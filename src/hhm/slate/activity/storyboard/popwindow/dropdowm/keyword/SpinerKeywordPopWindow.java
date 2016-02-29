package hhm.slate.activity.storyboard.popwindow.dropdowm.keyword;

import hhm.slate.R;
import hhm.slate.activity.storyboard.popwindow.dropdowm.keyword.AbstractKeywordSpinerAdapter.IKeywordOnItemSelectListener;
import hhm.slate.activity.storyboard.popwindow.dropdowm.shots.AbstractShotsSpinerAdapter.IShotsOnItemSelectListener;
import hhm.slate.db.entity.Shots;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;

public class SpinerKeywordPopWindow extends PopupWindow implements
		OnItemClickListener {

	private Context mContext;
	private ListView mListView;
	private NormalKeywordSpinerAdapter mAdapter;
	private IKeywordOnItemSelectListener mItemSelectListener;

	public SpinerKeywordPopWindow(Context context) {
		super(context);

		mContext = context;
		init();
	}

	public void setItemListener(IKeywordOnItemSelectListener listener) {
		mItemSelectListener = listener;
	}

	private void init() {
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.dropdown_shot_keyword, null);
		setContentView(view);
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);

		setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0x00);
		setBackgroundDrawable(dw);

		mListView = (ListView) view.findViewById(R.id.LV_dropdowm_shot_keyword);

		mAdapter = new NormalKeywordSpinerAdapter(mContext);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
	}

	public void refreshData(List<String> list, int selIndex) {
		if (list != null && selIndex != -1) {
			mAdapter.refreshData(list, selIndex);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int pos, long arg3) {
		dismiss();
		if (mItemSelectListener != null) {
			mItemSelectListener.onKeywordItemClick(pos);
		}
	}

}
