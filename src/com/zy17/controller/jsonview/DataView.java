package com.zy17.controller.jsonview;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-18 Time: 下午1:45 To
 * change this template use File | Settings | File Templates.
 */
public interface DataView {
	boolean hasView();

	Class<? extends BaseView> getView();

	Object getData();
}
