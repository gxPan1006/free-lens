package gx.mydemo.free_lens.stat.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gx.mydemo.free_lens.stat.dto.DataStat;
import gx.mydemo.free_lens.stat.service.SessionService;

@RestController
public class DataController {
	@Autowired
	private SessionService sessionService;


	@GetMapping("/session_count")
	public DataStat greeting(@RequestParam(value = "collection_name", defaultValue = "all") String collection_name) {
		return sessionService.stat_session(collection_name);
	}
}
