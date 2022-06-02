package hello.core.lifecycle;

import static org.junit.jupiter.api.Assertions.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.junit.jupiter.api.Test;

class NetworkClient {
	
	private String url;
	
	

	public NetworkClient() {
		System.out.println("������ ȣ��, url = " + url);
		
	}

	
	
	
	//���� ���۽� ȣ��
	public void connect() {
		System.out.println("connect: " + url);
	}
	
	public void call(String message) {
		System.out.println("call: " + url + " message = " + message);
	}
	
	//���� ����� ȣ��
	public void disConnect() {
		System.out.println("close: " + url);
	}



	public void setUrl(String url) {
		this.url = url;
	}

	@PostConstruct
	public void init() {
		System.out.println("init");
		 connect();
		 call("�ʱ�ȭ ���� �޽���");
	}
	
	@PreDestroy
	public void close() {
		System.out.println("close");
		 disConnect();
		 
	}
	

}
