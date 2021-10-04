package com.test.montyhall.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@ToString
@Data
@Configuration
public class MontyHallConfiguration {

  @Value("${gamestrategy.stay.iterations}")
  String stayIteration;
  @Value("${gamestrategy.change.iterations}")
  String changeIteration;

}
