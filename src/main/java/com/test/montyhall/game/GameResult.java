package com.test.montyhall.game;

import com.test.montyhall.enums.GameStrategy;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@EqualsAndHashCode
public class GameResult {

  private final GameStrategy gameStrategy;
  private final AtomicInteger win = new AtomicInteger(0);
  private final AtomicInteger lose = new AtomicInteger(0);

  public GameResult(GameStrategy gameStrategy) {
    this.gameStrategy = gameStrategy;
  }

  public void incrementWin() {
    win.incrementAndGet();
  }

  public void incrementLose() {
    lose.incrementAndGet();
  }

  @Override
  public String toString() {
    return "Game ended with strategy: "
        + getGameStrategy() + ", wins: "
        + getWin().get() + ", loses: " + lose.get();
  }
}
