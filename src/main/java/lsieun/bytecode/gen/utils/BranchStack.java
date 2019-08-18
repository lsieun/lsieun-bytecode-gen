package lsieun.bytecode.gen.utils;

import java.util.Hashtable;
import java.util.Stack;

import lsieun.bytecode.gen.opcode.Instruction;

public class BranchStack {
    private final Stack<BranchTarget> branchTargets = new Stack();
    private final Hashtable<Instruction, BranchTarget> visitedTargets = new Hashtable();


    public void push(final Instruction target, final int stackDepth) {
        if (visited(target)) {
            return;
        }
        branchTargets.push(visit(target, stackDepth));
    }

    public BranchTarget pop() {
        if (!branchTargets.empty()) {
            final BranchTarget bt = branchTargets.pop();
            return bt;
        }
        return null;
    }


    private BranchTarget visit(final Instruction target, final int stackDepth) {
        final BranchTarget bt = new BranchTarget(target, stackDepth);
        visitedTargets.put(target, bt);
        return bt;
    }


    private boolean visited(final Instruction target) {
        return visitedTargets.get(target) != null;
    }
}
