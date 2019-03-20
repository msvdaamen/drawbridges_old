package com.msvdaamen.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateBlockstate implements IMessage {
    private int stateId;
    private BlockPos blockPos;

    public PacketUpdateBlockstate() {

    }

    public PacketUpdateBlockstate(int newStateId, BlockPos pos) {
        this.stateId = newStateId;
        blockPos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        stateId = buf.readInt();
        blockPos = BlockPos.fromLong(buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(stateId);
        buf.writeLong(blockPos.toLong());
    }

    public static class Handler implements IMessageHandler<PacketUpdateBlockstate, IMessage> {

        public Handler() {}

        @Override
        public IMessage onMessage(PacketUpdateBlockstate message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketUpdateBlockstate message, MessageContext ctx) {
            World world = ctx.getServerHandler().player.getServerWorld();
            world.setBlockState(message.blockPos, world.getBlockState(message.blockPos).getBlock().getStateFromMeta(message.stateId));
        }
    }
}
