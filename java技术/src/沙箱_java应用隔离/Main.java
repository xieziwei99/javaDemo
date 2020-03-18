package 沙箱_java应用隔离;

/**
 * @author xieziwei99
 * 2020-03-18
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.setSecurityManager(new MySecurityManager());

        new UserAppThread().start();
        int i = 0;
        while (true) {
            i++;
            System.out.println("Java平台执行任务 "+ i +" ......");
            Thread.sleep(1000);
        }
    }
}

class UserAppThread extends Thread {
    @Override
    public void run() {
        System.out.println("用户应用正在运行");
        System.out.println("    ......");
        System.out.println("用户应用运行结束");
        System.out.println("准备退出");
        System.exit(-1);
        System.out.println("不会输出");
    }
}

class MySecurityManager extends SecurityManager {
    // 如果不允许调用线程使用指定的状态代码导致Java虚拟机停止，则抛出SecurityException
    @Override
    public void checkExit(int status) {
        super.checkExit(status);
        if (0 != status) {
            throw new SecurityException("不允许用户程序退出");
        }
    }
}