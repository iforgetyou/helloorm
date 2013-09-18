package com.zy17.controller.jsonview;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-18 Time: 下午1:45 To
 * change this template use File | Settings | File Templates.
 */
public class PojoView implements DataView {

	public PojoView(Object data, Class<? extends BaseView> view) {
		this.data = data;
		this.view = view;
	}

	private final Object data;
	private final Class<? extends BaseView> view;

	@Override
	public boolean hasView() {
		return true;
	}

	public Object getData() {
		return data;
	}

	public Class<? extends BaseView> getView() {
		return view;
	}
}
