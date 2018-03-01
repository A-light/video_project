package cn.sightseeing.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class EncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//处理post请求编码
		request.setCharacterEncoding("utf-8");
		HttpServletRequest req=(HttpServletRequest) request;
		if(req.getMethod().equals("GET")){
			EncodingRequest reqs=new EncodingRequest(req); 
			chain.doFilter(reqs, response);	
		}else if(req.getMethod().equals("POST")){
			chain.doFilter(request, response);
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
