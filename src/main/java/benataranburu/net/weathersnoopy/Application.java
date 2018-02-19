package benataranburu.net.weathersnoopy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import benataranburu.net.weathersnoopy.client.WeatherData;

public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			WeatherData weatherData = restTemplate.getForObject(
					"http://api.openweathermap.org/data/2.5/weather?" +
					"q=London&APPID=5b9c22bf6509ec725d2a5fa622ad5492" 
					, WeatherData.class);
			log.info(weatherData.toString());
		};
	}
}
