package cn.zy.cxf;

import javax.jws.WebService;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

@WebService
class HelloService  {
	public static void main(String[] args) {
		JaxWsServerFactoryBean bean=new JaxWsServerFactoryBean();
		bean.setAddress("http://127.0.0.1:1234/hello");
		bean.setServiceClass(HelloService.class);
		bean.setServiceBean(new HelloService());
		bean.create();
		System.out.println("helloService start!");
	}
}
