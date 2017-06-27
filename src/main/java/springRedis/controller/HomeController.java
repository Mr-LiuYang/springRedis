package springRedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import springRedis.exception.SpitterDuplicateException;
import springRedis.exception.SpitterNotFoundException;

import javax.annotation.Resource;

@RestController
@RequestMapping("home")
public class HomeController {

	@Autowired
	@Qualifier( "RedisTemplate" )
	private RedisTemplate redisTemplate;

	@Autowired
	private RestTemplate restTemplate;

	@Resource(name = "RedisTemplate")
	private ValueOperations valueOperations;

	@RequestMapping("/a")
	public ModelAndView test(ModelAndView modelAndView){
		modelAndView.setViewName("ho");
		return modelAndView;
	}

	@RequestMapping("hello")
	public String getHello(){
//		valueOperations.set("username","张");
//		redis.opsForValue().set("liuyang","刘洋");
		String name="李四";
		name= (String) valueOperations.get("liuyang");
		System.out.println(name);
		return "hello";
	}

	@RequestMapping("/liuyang/{value}")
	public String spitter(@PathVariable String value){
		if(!"hahaha".equals(value)){
			throw new SpitterNotFoundException();
		}
		throw new SpitterDuplicateException();
	}

	@RequestMapping("/rest")
	public String restful(){
		System.out.println("调用远程服务");
		HttpHeaders headers=restTemplate.headForHeaders("http://www.baidu.com");
		System.out.println(headers.getContentLength());
		return "123";
	}
}
