package travel.project.service;


public interface EmailService {
	
	public void sendMail(String emailRecipient, String emailTitle, String emailContent);
}
