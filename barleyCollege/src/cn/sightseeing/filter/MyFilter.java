package cn.sightseeing.filter;

import java.io.IOException;
import javax.servlet.*;

public class MyFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("过滤器死亡!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("就过滤你！不服上！");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("我来了！");
	}

}
