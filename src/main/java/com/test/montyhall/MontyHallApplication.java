package com.test.montyhall;

import com.test.montyhall.config.MontyHallConfiguration;
import com.test.montyhall.enums.GameStrategy;
import com.test.montyhall.game.Game;
import com.test.montyhall.game.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class MontyHallApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext configurableApplicationContext = SpringApplication
        .run(MontyHallApplication.class, args);

    MontyHallConfiguration montyHallConfiguration = configurableApplicationContext
        .getBean(MontyHallConfiguration.class);

    Player player = new Player();
    Game game = new Game();

    game.runGame(GameStrategy.STAY, Integer.parseInt(montyHallConfiguration.getStayIteration()), player);
    game.runGame(GameStrategy.CHANGE, Integer.parseInt(montyHallConfiguration.getChangeIteration()), player);

    game.printGameResultWithStay();
    game.printGameResultWithChange();
  }
}
