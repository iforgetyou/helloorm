package com.zy17.controller.jsonview;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-18 Time: 下午1:48 To
 * change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseView {
	public Class<? extends BaseView> value();
}
