package com.sxh.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * 测试使用JMH量化测试工具
 * https://mp.weixin.qq.com/s/k6t7yJLx73dWov835XVy3A
 * @author sxh
 * @date 2020/8/26
 */
@BenchmarkMode(Mode.AverageTime) // 执行模式
@Warmup(iterations = 3, time = 1) // 预热 3 轮，每次 1s
@Measurement(iterations = 5, time = 5) // 测试 5 轮，每次 5s
@Threads(4) // 4个线程
@Fork(1) // fork 1 个线程
@State(value = Scope.Benchmark) // 对象作用范围
@OutputTimeUnit(TimeUnit.NANOSECONDS) // 统计结果时间单位
public class JMHDemo {
    @Param(value = {"10", "50", "100"})
    private int length;

    @Benchmark
    public void testStringAdd(Blackhole blackhole) {
        String a = "";
        for (int i = 0; i < length; i++) {
            a += i;
        }
        blackhole.consume(a);
    }

    @Benchmark
    public void testStringBuilderAdd(Blackhole blackhole) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(i);
        }
        blackhole.consume(sb.toString());
    }

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(JMHDemo.class.getSimpleName()) // 导入测试类
                .result("result.json")
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();
    }
}
