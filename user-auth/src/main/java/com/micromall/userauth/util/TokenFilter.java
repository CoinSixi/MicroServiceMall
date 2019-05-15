package com.micromall.userauth.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micromall.apiservice.common.ServerResponse;
import com.micromall.apiservice.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*@Configuration
@WebFilter
public class TokenFilter  extends OncePerRequestFilter {
    @Autowired
    private RedisTokenManager redisTokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        if (request.getRequestURI().indexOf("/login/") >= 0) {
            filterChain.doFilter(request, response);
        }else if (request.getRequestURI().indexOf("/hi") >= 0) {
            filterChain.doFilter(request, response);
        }else {
        
            String accessToken = request.getHeader("Authorization");
            ServerResponse<User> result = null;
            if (null == accessToken) {
                result = ServerResponse.createByErrorCodeMessage(10, "token 不存在");
            } else {
                User user = this.redisTokenManager.getUserInfoByToken(accessToken);
                if (user != null) {
                    filterChain.doFilter(request, response);
                } else {
                	result = ServerResponse.createByErrorCodeMessage(10, "token已过期,请重新登录!");
                }
            }
            try {
                responseOutWithJson(response, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void responseOutWithJson(HttpServletResponse response,
                                       Object responseObject) throws Exception{
        //将实体对象转换为JSON Object转换
        ObjectMapper mapper = new ObjectMapper();
            String jsonStr= mapper.writeValueAsString(responseObject);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}*/
