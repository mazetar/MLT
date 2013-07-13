package com.mazetar.mazLearnedThis.dimensions.gen.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenStoneHouse extends WorldGenerator {
    public WorldGenStoneHouse() {

    }

    public boolean generate(World world, Random rand, int i, int j, int k) {
        // This states the parameters that must be met if the structure is to
        // generate, i.e. The block underneath each corner of the house must be
        // sand
        // i, j, and k are the x, y, and z coordinates of the structure. Think
        // of a 3d area, where points i, k, and k are coordinates (0,0,0).
        // Therefore, for example,
        // "world.getBlockId(i + 9, j, k) != Block.sand.blockID" states the the
        // block UNDERNEATH the block that is at the coordinates (9,0,0) must be
        // sand.
        // "world.getBlockId(i + 9, j + 1, k) != 0" Where there is a '0' in
        // place of the usual Block.sand.blockID, this means air, or, nothing,
        // stating the block ABOVE a certain coordinate must be air.
        int blockID = Block.blockClay.blockID;
        if (world.getBlockId(i, j, k) != blockID
                || world.getBlockId(i, j + 1, k) != 0
                || world.getBlockId(i + 9, j, k) != blockID
                || world.getBlockId(i + 9, j, k + 9) != blockID
                || world.getBlockId(i, j, k + 9) != blockID
                || world.getBlockId(i + 9, j + 1, k) != 0
                || world.getBlockId(i + 9, j + 1, k + 9) != 0
                || world.getBlockId(i, j + 1, k + 9) != 0) {
            return false;
        }
        
        if (rand.nextInt(11) < 10)
            return false;
        
        System.out.println("House @ " + i + ", " + j + ", " + k);
        // We are about to create a new integer 'i1'. As many of these can be
        // created as you like, i2, i3, i4 etc. What the line
        // "for(int i1 = 0; i1 < 9; i1++)" is saying is to
        // extend i1 starting at 0, and continue up to less than 9 (i.e. 8),
        // increasing by one each time (i1++). This effectively creates a
        // straight line along i (the x axis) from points
        // 0 to 8. You will see why this will become very useful later on.
        for (int i1 = 0; i1 < 9; i1++) {
            for (int j1 = 0; j1 < 6; j1++) // This creates a new integer j1
                                           // within i1, and therefore creates a
                                           // two dimensional rectangle, which
                                           // is 8 wide and 5 high, as just
                                           // stated.
            {
                for (int k1 = 0; k1 < 9; k1++) // This creates yet another
                                               // integer, k1, within the
                                               // rectangle, as it is
                                               // 'ksomething', it is along the
                                               // z axis. So we now have a three
                                               // dimensional
                // box, 8 wide, 5 high, and 8 thick. This is the main body of
                // our brick house, which is 8x5x8.
                {
                    if ((i1 >= 1 && i1 <= 7) && (j1 >= 1 && j1 <= 4)
                            && (k1 >= 1 && k1 <= 7)) // "(i1 >= 1 && i1 <= 7)"
                                                     // Simply means that i1 is
                                                     // greater than or equal to
                                                     // one, AND less than or
                    // equal to 7. Similar is done for the y and z axis (j1 and
                    // k1). So this if statement defines an area
                    // inside our 8x5x8 box. The 'continue' bit below simply
                    // means 'ignore this area'. So now we have
                    // a hollow 8x5x8 box, which we need to actually generate in
                    // the world.
                    {
                        continue;
                    }
                    world.setBlock(i + i1, j + j1, k + k1, Block.stone.blockID); // This
                                                                                 // defines
                                                                                 // what
                                                                                 // material
                                                                                 // our
                                                                                 // hollow
                                                                                 // box
                                                                                 // will
                                                                                 // be
                                                                                 // made
                                                                                 // out
                                                                                 // of,
                                                                                 // in
                                                                                 // this
                                                                                 // case,
                                                                                 // brick.
                                                                                 // Our
                                                                                 // integers
                                                                                 // (i1,
                                                                                 // j1
                                                                                 // and
                                                                                 // k1)
                    // that we just created are added i, j, and k. Remember that
                    // these act as the centre point (0,0,0), so you are
                    // stating what set of coordinates you want to be filled
                    // with brick blocks, which we just defined.

                    world.setBlock(i + 6, j + 2, k + 8, Block.glass.blockID); // This
                                                                              // created
                                                                              // the
                                                                              // windows.
                                                                              // This
                                                                              // first
                                                                              // glass
                                                                              // block
                                                                              // has
                                                                              // the
                                                                              // coordinates
                                                                              // (i
                                                                              // +
                                                                              // 6,
                                                                              // j
                                                                              // +
                                                                              // 2,
                                                                              // k
                                                                              // +
                                                                              // 8).
                                                                              // As
                                                                              // i,
                                                                              // j,
                                                                              // and
                                                                              // k
                                                                              // are
                    world.setBlock(i + 2, j + 2, k + 8, Block.glass.blockID); // the
                                                                              // centre
                                                                              // points,
                                                                              // the
                                                                              // coordinates
                                                                              // are
                                                                              // (0
                                                                              // +
                                                                              // 6,
                                                                              // 0
                                                                              // +
                                                                              // 2,
                                                                              // and
                                                                              // k
                                                                              // +
                                                                              // 8),
                                                                              // or
                                                                              // (6,2,8).
                                                                              // The
                                                                              // actual
                                                                              // point
                                                                              // (0,0,0)
                                                                              // is
                                                                              // at
                    world.setBlock(i + 6, j + 3, k + 8, Block.glass.blockID); // the
                                                                              // back
                                                                              // left
                                                                              // corner
                                                                              // of
                                                                              // the
                                                                              // box
                                                                              // we
                                                                              // created,
                                                                              // so
                                                                              // the
                                                                              // first
                                                                              // glass
                                                                              // block
                                                                              // is
                                                                              // 6
                                                                              // blocks
                                                                              // right,
                                                                              // 2
                                                                              // blocks
                                                                              // up,
                    world.setBlock(i + 2, j + 3, k + 8, Block.glass.blockID); // and
                                                                              // 8
                                                                              // blocks
                                                                              // in
                                                                              // front
                                                                              // of
                                                                              // that
                                                                              // block.
                                                                              // Trust
                                                                              // me,
                                                                              // it'll
                                                                              // get
                                                                              // easier
                                                                              // the
                                                                              // more
                                                                              // you
                                                                              // do.

                    world.setBlock(i + 3, j + 0, k + 0,
                            Block.netherrack.blockID); // The same as above,
                                                       // instead the block is
                                                       // changed to netherrack
                                                       // to create the
                                                       // fireplace base.
                    world.setBlock(i + 4, j + 0, k + 0,
                            Block.netherrack.blockID);
                    world.setBlock(i + 5, j + 0, k + 0,
                            Block.netherrack.blockID);

                    world.setBlock(i + 3, j + 1, k + 0, Block.fire.blockID); // Same
                                                                             // as
                                                                             // above
                    world.setBlock(i + 4, j + 1, k + 0, Block.fire.blockID);
                    world.setBlock(i + 5, j + 1, k + 0, Block.fire.blockID);

                    world.setBlock(i + 6, j + 1, k + 1, Block.brick.blockID); // Same
                                                                              // as
                                                                              // above.
                                                                              // This
                                                                              // creates
                                                                              // a
                                                                              // cover
                                                                              // for
                                                                              // the
                                                                              // fireplace
                                                                              // inside
                                                                              // the
                                                                              // house.
                    world.setBlock(i + 6, j + 2, k + 1, Block.brick.blockID);
                    world.setBlock(i + 6, j + 3, k + 1, Block.brick.blockID);
                    world.setBlock(i + 5, j + 3, k + 1, Block.brick.blockID);
                    world.setBlock(i + 4, j + 3, k + 1, Block.brick.blockID);
                    world.setBlock(i + 3, j + 3, k + 1, Block.brick.blockID);
                    world.setBlock(i + 2, j + 3, k + 1, Block.brick.blockID);
                    world.setBlock(i + 2, j + 2, k + 1, Block.brick.blockID);
                    world.setBlock(i + 2, j + 1, k + 1, Block.brick.blockID);
                    world.setBlock(i + 3, j + 2, k + 0, 0); // Remember what I
                                                            // said about the 0
                                                            // replacing the
                                                            // Block.whateverblock.blockID?
                                                            // This means that
                                                            // the block in the
                                                            // coordinates
                                                            // stated will
                    world.setBlock(i + 4, j + 2, k + 0, 0); // be air.
                    world.setBlock(i + 5, j + 2, k + 0, 0);
                    world.setBlock(i + 3, j + 3, k + 0, 0);
                    world.setBlock(i + 4, j + 3, k + 0, 0);
                    world.setBlock(i + 5, j + 3, k + 0, 0);
                    world.setBlock(i + 4, j + 1, k + 8, 0);
                    world.setBlock(i + 4, j + 2, k + 8, 0);

                    // The furnishings for the house, same as above.
                    world.setBlock(i + 7, j + 1, k + 7, Block.fence.blockID);
                    world.setBlock(i + 7, j + 2, k + 7, Block.cake.blockID);
                    world.setBlock(i + 7, j + 1, k + 3,
                            Block.stairsWoodOak.blockID);
                    world.setBlock(i + 7, j + 1, k + 4,
                            Block.stairsWoodOak.blockID);
                    world.setBlock(i + 7, j + 1, k + 5,
                            Block.stairsWoodOak.blockID);
                    world.setBlock(i + 1, j + 1, k + 7, Block.workbench.blockID);
                    world.setBlock(i + 1, j + 1, k + 5, Block.fence.blockID);
                    world.setBlock(i + 1, j + 2, k + 5,
                            Block.pressurePlateStone.blockID);

                }

                // Ok, so here we create another integer, 'i2'. As above, it
                // starts at to point 2 on the x axis, and extends until 6.
                // Basically every time I want to create a new
                // straight line, I create a new integer 'nx', where 'n' is i, j
                // or k depending on which axis the line is on, and x is the
                // next whole number up from the integer
                // before.
                for (int i2 = 2; i2 < 7; i2++) {
                    world.setBlock(i + i2, j + 2, k - 1, Block.brick.blockID); // So
                                                                               // here,
                                                                               // when
                                                                               // we
                                                                               // define
                                                                               // the
                                                                               // x
                                                                               // coordinate,
                                                                               // we
                                                                               // put
                                                                               // '+
                                                                               // i2',
                                                                               // which
                                                                               // states
                                                                               // that
                                                                               // the
                                                                               // x
                                                                               // coordinate
                                                                               // is
                                                                               // every
                                                                               // value
                    world.setBlock(i + i2, j + 1, k - 1, Block.brick.blockID); // from
                                                                               // 2
                                                                               // to
                                                                               // 6,
                                                                               // therefore
                                                                               // making
                                                                               // a
                                                                               // straight
                                                                               // line.
                                                                               // We
                                                                               // then
                                                                               // give
                                                                               // the
                                                                               // y
                                                                               // and
                                                                               // z
                                                                               // values
                                                                               // of
                                                                               // this
                                                                               // line.
                    world.setBlock(i + i2, j + 3, k - 1, Block.brick.blockID);
                }

                for (int i3 = 0; i3 < 9; i3++) // Same as above, this codes for
                                               // the mini garden bit at the
                                               // front of the house.
                {
                    world.setBlock(i + i3, j + 1, k + 12, Block.fence.blockID);
                    world.setBlock(i + 4, j + 1, k + 12,
                            Block.fenceGate.blockID);
                    world.setBlock(i + 0, j + 1, k + 9, Block.fence.blockID);
                    world.setBlock(i + 0, j + 1, k + 10, Block.fence.blockID);
                    world.setBlock(i + 8, j + 1, k + 9, Block.fence.blockID);
                    world.setBlock(i + 8, j + 1, k + 10, Block.fence.blockID);
                    world.setBlock(i + 0, j + 1, k + 11, Block.fence.blockID);
                    world.setBlock(i + 8, j + 1, k + 11, Block.fence.blockID);
                    // The lampost things at the front of the house
                    world.setBlock(i + 2, j + 2, k + 12, Block.fence.blockID);
                    world.setBlock(i + 6, j + 2, k + 12, Block.fence.blockID);
                    world.setBlock(i + 2, j + 3, k + 12, Block.planks.blockID);
                    world.setBlock(i + 6, j + 3, k + 12, Block.planks.blockID);
                    world.setBlock(i + 2, j + 3, k + 13,
                            Block.torchWood.blockID);
                    world.setBlock(i + 6, j + 3, k + 13,
                            Block.torchWood.blockID);
                }

                for (int i4 = 0; i4 < 9; i4++) {
                    for (int k2 = 9; k2 < 13; k2++) {
                        world.setBlock(i + i4, j + 0, k + k2,
                                Block.grass.blockID);
                        world.setBlock(i + 1, j + 1, k + 9,
                                Block.plantRed.blockID);
                        world.setBlock(i + 3, j + 1, k + 9,
                                Block.plantRed.blockID);
                        world.setBlock(i + 5, j + 1, k + 9,
                                Block.plantRed.blockID);
                        world.setBlock(i + 7, j + 1, k + 9,
                                Block.plantRed.blockID);
                        world.setBlock(i + 2, j + 1, k + 9,
                                Block.plantYellow.blockID);
                        world.setBlock(i + 6, j + 1, k + 9,
                                Block.plantYellow.blockID);
                        // Makes sure that there are no blocks in front of the
                        // fence for 4 blocks, by replacing theme with air.
                        world.setBlock(i + i4, j + 1, k + k2 + 4, 0);

                    }
                }

                for (int j2 = 1; j2 < 4; j2++) // Codes for the bookshelves.
                                               // Here we create a new line on
                                               // the y axis, j2. We then place
                                               // this at x coordinates 1 and 7,
                                               // either side of the fireplace.
                {
                    world.setBlock(i + 7, j + j2, k + 1,
                            Block.bookShelf.blockID);
                    world.setBlock(i + 1, j + j2, k + 1,
                            Block.bookShelf.blockID);
                }

                // The rest of the code all codes for the chest in the room.
                world.setBlockMetadataWithNotify(i + 1, j + 1, k + 3,
                        Block.chest.blockID, 0); // This states what coordinates
                                                 // the chest shall be, like
                                                 // we're used to
                TileEntityChest tileentitychest = new TileEntityChest(); // This
                                                                         // defines
                                                                         // a
                                                                         // new
                                                                         // variable
                                                                         // 'tile
                                                                         // entity
                                                                         // chest'.
                                                                         // Don't
                                                                         // worry
                                                                         // too
                                                                         // much
                                                                         // about
                                                                         // it.
                world.setBlockTileEntity(i + 1, j + 1, k + 3, tileentitychest); // This
                                                                                // states
                                                                                // where
                                                                                // this
                                                                                // tile
                                                                                // entity
                                                                                // should
                                                                                // be,
                                                                                // which
                                                                                // is
                                                                                // the
                                                                                // same
                                                                                // coordinates
                                                                                // as
                                                                                // the
                                                                                // chest.

                for (int g = 1; g < tileentitychest.getSizeInventory(); g++) // As
                                                                             // above,
                                                                             // creates
                                                                             // a
                                                                             // new
                                                                             // integer
                                                                             // 'g'.
                {
                    int h = rand.nextInt(100); // Think of this as the item has
                                               // a chance out of 100 to spawn
                    if (h >= 1 && h <= 5) // This says that if h (as defined in
                                          // the above line) is between one and
                                          // five, a stack of the item will
                                          // spawn. Therefore the chance of it
                                          // spawning is 5 out of 100.
                    {
                        tileentitychest.setInventorySlotContents(
                                g,
                                new ItemStack(Item.porkCooked.itemID, rand
                                        .nextInt(5) + 1, 0)); // This says what
                                                              // the said stack
                                                              // is of, and how
                                                              // big it is
                    } // The stack will be atleast 1 (+1) plus any number
                      // between
                    if (h >= 6 && h <= 10) // 0 and 5 (rand.nextInt(5)).
                    {
                        tileentitychest.setInventorySlotContents(
                                g,
                                new ItemStack(Block.torchWood.blockID, rand
                                        .nextInt(10) + 1, 0));
                    }
                    if (h >= 11 && h <= 12) {
                        tileentitychest.setInventorySlotContents(g,
                                new ItemStack(Item.swordGold.itemID, 1, 0)); // Here
                                                                             // there
                                                                             // is
                                                                             // no
                                                                             // rand.nextInt
                                                                             // because
                                                                             // I
                                                                             // want
                                                                             // there
                                                                             // to
                                                                             // always
                                                                             // be
                                                                             // only
                                                                             // one
                    } // of the item per stack.
                    if (h >= 13 && h <= 20) {
                        tileentitychest.setInventorySlotContents(
                                g,
                                new ItemStack(Item.arrow.itemID, rand
                                        .nextInt(10) + 1, 0));
                    }
                    if (h >= 21 && h <= 23) {
                        tileentitychest.setInventorySlotContents(
                                g,
                                new ItemStack(Item.redstone.itemID, rand
                                        .nextInt(4) + 1, 0));
                    }
                }

            }

        }
        return true;
    }

}