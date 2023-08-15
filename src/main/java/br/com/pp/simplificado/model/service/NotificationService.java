package br.com.pp.simplificado.model.service;

import org.springframework.stereotype.Service;

import br.com.pp.simplificado.exception.BusinessException;
import br.com.pp.simplificado.model.data.User;

@Service
public class NotificationService extends BaseService {
//	@Autowired
//	private RestTemplate restTemplate;
	
	public void notify(User user, String message) throws BusinessException {
		System.out.println(message);
		
//		String email = user.getEmail();
		
//		NotificationDto notificationDto = new NotificationDto(email, message);
		
//		ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(
//				Constants.REST_URL_NOTIFICATION, notificationDto, String.class);
//		if (responseEntity.getStatusCode() != HttpStatus.OK) {
//			System.out.println("Serviço de notificação indisponível");
//			throw new BusinessException("Serviço de notificação indisponível");
//		}
	}
}
