package transage.com.mvp_retrofit_rxjava.model;

import transage.com.mvp_retrofit_rxjava.bean.MovieBean;

/**
 * Created by dongrp on 2017/3/10.
 */

public interface Top250Listener {

    void top250Success(MovieBean movieBean);

    void top250Fail();
}
