import com.example.lottery.service.LotteryService;

module com.example.app {
	requires com.example.lottery;
	requires com.example.random;
	uses LotteryService;
}