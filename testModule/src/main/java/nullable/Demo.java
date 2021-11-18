package nullable;

import org.junit.Test;

import java.util.Optional;

/**
 * 空指针处理
 * @author sxh
 * @date 2021/11/18
 */
public class Demo {
    @Test
    public void fun() {
        Bottle bottle = getBottle();
        if (bottle != null) {
            System.out.println(bottle);
        }
        // 创建Optional对象
        Optional<Bottle> optionalBottle = Optional.ofNullable(bottle);
        // 检查对象对否为空
        if (optionalBottle.isPresent()) {
            System.out.println("isPresent() ---> bottle不为空！");
        }
        // 对象不为空，执行方法体，无返回值
        optionalBottle.ifPresent(item -> {
            System.out.println("ifPresent() ---> bottle不为空！");
        });
        // 若bottle为空，返回orElse()中定义的对象；若不为空，返回原对象。
        bottle = optionalBottle.orElse(new Bottle(10, 1, true));
        System.out.println(bottle);
    }

    private Bottle getBottle() {
        return null;
    }
}
