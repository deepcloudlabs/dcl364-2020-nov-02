package com.example.stockmarket.config;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.stockmarket.batch.StockItemProcessor;
import com.example.stockmarket.batch.StockItemReader;
import com.example.stockmarket.batch.StockItemWriter;
import com.example.stockmarket.entity.Stock;
import com.example.stockmarket.repository.StockRepository;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {
	@Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;
    
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired private Job stockEmulationJob;
    
    @Bean
    public Job stockEmulationJob(Step taskletStep,Step chunkStep) {
        return jobBuilders.get("stockEmulationJob")
            .start(taskletStep)
            .next(chunkStep)
            .build();
    }

    @Bean
    public Step taskletStep(Tasklet tasklet) {
        return stepBuilders.get("taskletStep")
            .tasklet(tasklet)
            .build();
    }
    
    @Bean
    public Step chunkStep(ItemReader<Stock> reader, ItemProcessor<Stock, Stock> processor,ItemWriter<Stock> writer) {
        return stepBuilders.get("chunkStep")
            .<Stock, Stock>chunk(20)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }
    
    @StepScope
    @Bean
    public ItemReader<Stock> reader(StockRepository stockRepository) {
        return new StockItemReader(stockRepository);
    }
    
    @StepScope
    @Bean
    public ItemProcessor<Stock, Stock> processor() {
        return new StockItemProcessor();
    }
 
    
    @StepScope
    @Bean
    public ItemWriter<Stock> writer(StockRepository stockRepository) {
        return new StockItemWriter(stockRepository);
    }
    
    @Bean
    public Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            return RepeatStatus.FINISHED;
        };
    }
    
	@Scheduled(fixedRate = 5000)
	public void run() throws Exception {
	    jobLauncher.run(stockEmulationJob,new JobParametersBuilder().addString("id",UUID.randomUUID().toString() ).toJobParameters());
	}
}
