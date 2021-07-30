package com.sxh.zk;

/**
 * Zookeeper基础Demo
 *
 * @author sxh
 * @date 2021/7/30
 */

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 *
 * @author LiJie
 *
 */
public class ZkDemo {

    private static final String connectString = "localhost:2181,localhost:2181,localhost:2181";

    private static final int sessionTimeout = 50000;

    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        String node = "/myTest";
        //创建节点
        create(node);
        //获取子节点
        getAllNode();
        //修改
        updateData(node);
        //删除
        delete(node);
        System.out.println("节点" + node + "是否存在: " + isExist(node));
    }

    /**
     * 获取zookeeper实例
     * @return
     * @throws Exception
     */
    public static ZooKeeper getZookeeper() throws Exception {
        if (zk == null) {
            zk = new ZooKeeper(connectString, sessionTimeout, event -> {
                // 收到watch通知后的回调函数
                System.out.println("事件类型" + event.getType() + "，路径" + event.getPath());

                //因为监听器只会监听一次，这样可以一直监听,且只监听"/"目录
                try {
                    zk.getChildren("/", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return zk;
    }

    /**
     * 创建数据
     * @throws Exception
     */
    public static void create(String node) throws Exception {
        if (isExist(node)) {
            System.out.println("节点" + node + "已存在！");
            return;
        }
        //参数1->路径，参数2->内容，参数3->权限，参数4->类型
        String znodePath = getZookeeper().create(node, "hello zookeeper".getBytes(), Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);
        System.out.println("返回的路径为：" + znodePath);
    }

    /**
     * 判断znode是否存在
     * @throws Exception
     */
    public static Boolean isExist(String node) throws Exception {
        ZooKeeper zk = getZookeeper();
        Stat exists = zk.exists(node, false);
        return exists != null;
    }

    /**
     * 获取子节点
     * @throws Exception
     */
    public static void getAllNode() throws Exception {
        //获取子节点
        List<String> children = getZookeeper().getChildren("/", true);
        for (String string : children) {
            System.out.println("子节点:" + string);
        }
    }

    /**
     * 打印node节点数据
     * @throws Exception
     */
    public static void printNodeData(String node) throws Exception {
        byte[] data = getZookeeper().getData(node, false, new Stat());
        String result = new String(data);
        System.out.println("节点" + node + "数据：" + result);
    }

    /**
     * 删除数据
     * @throws Exception
     */
    public static void delete(String node) throws Exception {
        //第二个参数为version，-1表示删除所有版本
        //它不支持删除的节点下面还有子节点，只能递归删除
        getZookeeper().delete(node, -1);
    }

    /**
     * 修改znode的值
     * @throws Exception
     */
    public static void updateData(String node) throws Exception {
        System.out.println("修改后：");
        printNodeData(node);
        getZookeeper().setData(node, "modify data".getBytes(), -1);
        System.out.println("修改后：");
        printNodeData(node);
    }
}

