import com.example.lottery.service.LotteryService;
import com.example.lottery.service.business.SimpleLotteryService;
import com.example.random.service.RandomNumberService;

module com.example.lottery {
	exports com.example.lottery.service;
	requires com.example.random;
	provides LotteryService with SimpleLotteryService;
    uses RandomNumberService;
}