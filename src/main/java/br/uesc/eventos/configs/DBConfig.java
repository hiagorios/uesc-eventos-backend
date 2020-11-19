package br.uesc.eventos.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.uesc.eventos.service.StartupService;

@Configuration
public class DBConfig {

	@Autowired
	private StartupService startupService;
	
	@Bean
	@Profile("dev")
	public boolean instantiateDevDatabase() throws Exception {
		startupService.instantiateDevDatabase();
		return true;
	}
	
	@Bean
    @Profile("test")
    public boolean instantiateTestDatabase() throws Exception {
        startupService.instantiateTestDatabase();
        return true;
    }
}
