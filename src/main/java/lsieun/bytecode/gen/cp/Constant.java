package lsieun.bytecode.gen.cp;


public abstract class Constant {
    /**
     * <p>问题：为什么tag会是public final byte呢？</p>
     * <p>回答：</p>
     * <p>（1）final，保证了tag值的不能再进行改变了</p>
     * <p>（2）public，使用tag字段比getTag()更直观</p>
     * <p>（3）byte，由于tag的取值范围是1~20，因此使用byte就足够了</p>
     */
    public final byte tag;
    private int index;

    Constant(final byte tag) {
        this.tag = tag;
    }
}
