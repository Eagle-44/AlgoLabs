package com.company;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeTest {

    public BinarySearchTree tree;

    @Before

    public void init(){
        tree = new BinarySearchTree();
        tree.insertNode(27);
        tree.insertNode(12);
        tree.insertNode(32);
    }

    @Test
    public void testInsert(){
        Assert.assertEquals(27, tree.getRoot().getKey());
        Assert.assertEquals(12, tree.getRoot().getLeft().getKey());
        Assert.assertEquals(32, tree.getRoot().getRight().getKey());
    }

    @Test
    public void testFindNode(){
        tree.insertNode(33);
        Assert.assertEquals(27,tree.findNode(27).getKey());
        Assert.assertEquals(12,tree.findNode(12).getKey());
        Assert.assertEquals(32,tree.findNode(32).getKey());
        Assert.assertEquals(33,tree.findNode(33).getKey());

    }
}