package com.test.montyhall.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PlayerTest {

  @Test
  void testCreatingObjectIsSuccessful() {

    Player player = new Player();
    assertNotNull(player);

    player.setSelectedBox(1);
    assertEquals(1, player.getSelectedBox());
  }

}
