package com.test.montyhall.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.test.montyhall.enums.Prize;
import org.junit.jupiter.api.Test;

class BoxTest {

  @Test
  void testCreatingObjectIsSuccessful() {

    Box box = new Box(Prize.MONEY);
    assertNotNull(box);

    assertEquals(Prize.MONEY, box.getPrize());
  }
}
