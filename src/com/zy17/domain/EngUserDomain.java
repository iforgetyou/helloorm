package com.zy17.domain;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-11-18
 * Time: 上午11:01
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class EngUserDomain extends Base{

}
