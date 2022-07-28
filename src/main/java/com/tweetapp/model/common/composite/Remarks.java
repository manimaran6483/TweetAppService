package com.tweetapp.model.common.composite;

import java.util.ArrayList;
import java.util.List;

import com.tweetapp.model.common.atomic.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Remarks {

	private List<Message> messages = new ArrayList<>();

}
