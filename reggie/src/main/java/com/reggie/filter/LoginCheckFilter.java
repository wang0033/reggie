package com.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.reggie.common.BaseContext;
import com.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否完成登录
 * @author 86182
 * @create 2022/8/14 15:05
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        //1、获取本次请求的URI
        String requestURI = httpServletRequest.getRequestURI();
        String[] noNeedCheckUri = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };

        //2、判断本次请求是否需要处理
        boolean check = check(noNeedCheckUri, requestURI);

        //3、如果不需要处理，直接放行
        if (check){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //4、判断登录状态，如果已经登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("employee") != null){
            long empId = (long) httpServletRequest.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //4、判断移动端登录状态，如果已经登录，则直接放行
        if (httpServletRequest.getSession().getAttribute("user") != null){
            long userId = (long) httpServletRequest.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        log.info("拦截到请求：{}", requestURI);
    }

    /**
     * 判断是否需要登录
     * @param urls 不需要登录的url数组
     * @param requestUrl 请求的uri
     * @return
     */
    public boolean check(String[] urls, String requestUrl){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestUrl);
            if (match){
                return true;
            }
        }
        return false;
    }
}
