package com.ssafy.home.interceptor;

import com.ssafy.home.member.dto.Member;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러 메서드에 매핑된 uri가 호출 되면 실행 되는 메서드 controller 진입 직전 수행
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member member = (Member) session.getAttribute("member");
            if (member.getName() == null) {
                throw new NotFoundException("no validate session");
            }
            return true;
        }
        throw new NotFoundException("no session in header");
    }
}