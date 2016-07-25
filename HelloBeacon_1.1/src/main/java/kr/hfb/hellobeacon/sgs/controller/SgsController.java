package kr.hfb.hellobeacon.sgs.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.hfb.hellobeacon.common.bean.CommonBean;
import kr.hfb.hellobeacon.common.util.AppContextUtil;
import kr.hfb.hellobeacon.sgs.service.SgsService;

@Controller("sgsController")
public class SgsController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("#{config['server.domain']}")
	private String domain = null;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, value = "/{interfaceName}/{interfaceCode}")
	@ResponseBody
	public CommonBean call(HttpServletRequest request, HttpServletResponse response, @PathVariable String interfaceName, @PathVariable String interfaceCode, @RequestParam Map<String, Object> parameters) {
		logger.info("CALL IF NAME : " + "IF_" + interfaceName.toUpperCase() + "_" + interfaceCode.toUpperCase() + "_Service");
		SgsService service = (SgsService) AppContextUtil.getInstance().getBean("IF_" + interfaceName.toUpperCase() + "_" + interfaceCode.toUpperCase() + "_Service");
		service.preExecute(parameters);
		CommonBean bean = service.execute(request, parameters);

		// 가입자 인증 시 로그인 페이지로 이동
		if (request.getRequestURI().contains("/member/auth.json")) {
			try {
				if (bean.getCode().equals("000")) {
					response.sendRedirect(domain + "auth.html?success");
				} else {
					response.sendRedirect(domain + "auth.html?fail");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bean;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = "/r")
	@ResponseBody
	public ResponseEntity<String> call(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> parameters) {
		logger.info("CALL IF NAME : " + "IF_BEACON_PAGE_Service");
		SgsService service = (SgsService) AppContextUtil.getInstance().getBean("IF_BEACON_PAGE_Service");
		parameters.put("requestUrl", request.getRequestURI() + "?" + request.getQueryString());

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (null == ipAddress) {
			ipAddress = request.getRemoteAddr();
		}
		parameters.put("ipAddress", ipAddress);

		service.preExecute(parameters);
		String html = service.execute(parameters);

		if (null == html) {
			try {
				response.sendError(404);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "html", Charset.forName("utf-8"));
		headers.setContentType(mediaType);
		return new ResponseEntity<String>(html, headers, HttpStatus.OK);
	}
}
