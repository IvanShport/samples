package ru.mail.park.java.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoryLoadController {

	@GetMapping("/allocate/{mbs}")
	public int allocate(@PathVariable int mbs) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int dummyresult = 0;
		for (int i = 0; i < mbs; i++) {
			byte[] mb = new byte[1024 * 1024];
			random.nextBytes(mb);
			dummyresult += sum(mb);
		}
		return dummyresult;
	}

	private static int sum(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < bytes.length; i++) {
			result += bytes[i];
		}
		return result;
	}

}
