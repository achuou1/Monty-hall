package com.test.montyhall.game;

import com.test.montyhall.enums.Prize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@Builder
@ToString
@Data
@AllArgsConstructor
public class Box {

  private Prize prize;
}
