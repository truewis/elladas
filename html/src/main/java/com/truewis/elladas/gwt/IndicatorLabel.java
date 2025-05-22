package com.truewis.elladas.gwt;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import ktx.scene2d.KTable;
import ktx.scene2d.KWidget;
import ktx.scene2d.Scene2DSkin;
import ktx.scene2d.KTable.DefaultImpls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
    mv = {2, 1, 0},
    k = 1,
    xi = 48,
    d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0011"},
    d2 = {"LIndicatorLabel;", "Lcom/badlogic/gdx/scenes/scene2d/ui/Table;", "Lktx/scene2d/KTable;", "faceRight", "", "<init>", "(Z)V", "label", "Lcom/badlogic/gdx/scenes/scene2d/ui/Label;", "getLabel", "()Lcom/badlogic/gdx/scenes/scene2d/ui/Label;", "setLabel", "(Lcom/badlogic/gdx/scenes/scene2d/ui/Label;)V", "updateValues", "", "value", "", "Sources of elladas.core.main"}
)
@SourceDebugExtension({"SMAP\nIndicatorLabel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IndicatorLabel.kt\nIndicatorLabel\n+ 2 factory.kt\nktx/scene2d/FactoryKt\n*L\n1#1,58:1\n346#2,10:59\n99#2,2:69\n218#2,9:71\n99#2,2:80\n218#2,9:82\n99#2,2:91\n346#2,10:93\n99#2,2:103\n*S KotlinDebug\n*F\n+ 1 IndicatorLabel.kt\nIndicatorLabel\n*L\n19#1:59,10\n19#1:69,2\n22#1:71,9\n22#1:80,2\n29#1:82,9\n29#1:91,2\n33#1:93,10\n33#1:103,2\n*E\n"})
public final class IndicatorLabel extends Table implements KTable {
    public Label label;

    public IndicatorLabel(boolean faceRight) {
        if (faceRight) {
            KWidget $this$label_u24default$iv = (KWidget)this;
            CharSequence text$iv = (CharSequence)"";
            String style$iv = "default";
            Skin skin$iv = Scene2DSkin.INSTANCE.getDefaultSkin();
            int $i$f$label = 0;
            Actor actor$iv$iv = (Actor)(new Label(text$iv, skin$iv, style$iv));
            int $i$f$actor = 0;
            Cell var10002 = (Cell)$this$label_u24default$iv.storeActor(actor$iv$iv);
            Label var10001 = (Label)actor$iv$iv;
            int var12 = 0;
            this.setLabel((Label)actor$iv$iv);
            $this$label_u24default$iv = (KWidget)this;
            String drawableName$iv = "tree-plus";
            Skin skin$iv = Scene2DSkin.INSTANCE.getDefaultSkin();
            int $i$f$image = 0;
            Actor actor$iv$iv = (Actor)(new Image(skin$iv.getDrawable(drawableName$iv)));
            int $i$f$actor = 0;
            Cell it = (Cell)$this$label_u24default$iv.storeActor(actor$iv$iv);
            Image $this$_init__u24lambda_u241 = (Image)actor$iv$iv;
            int $this$_init__u24lambda_u240 = 0;
            it.pad(10.0F);
            $this$_init__u24lambda_u241.setScale(2.0F);
            Image var10000 = (Image)actor$iv$iv;
        } else {
            KWidget $this$image_u24default$iv = (KWidget)this;
            String drawableName$iv = "tree-plus";
            Skin skin$iv = Scene2DSkin.INSTANCE.getDefaultSkin();
            int $i$f$image = 0;
            Actor actor$iv$iv = (Actor)(new Image(skin$iv.getDrawable(drawableName$iv)));
            int $i$f$actor = 0;
            Cell it = (Cell)$this$image_u24default$iv.storeActor(actor$iv$iv);
            Image $this$_init__u24lambda_u242 = (Image)actor$iv$iv;
            int var37 = 0;
            it.pad(10.0F);
            $this$_init__u24lambda_u242.setScale(-2.0F, 2.0F);
            Image var39 = (Image)actor$iv$iv;
            $this$image_u24default$iv = (KWidget)this;
            CharSequence text$iv = (CharSequence)"";
            String style$iv = "default";
            Skin skin$iv = Scene2DSkin.INSTANCE.getDefaultSkin();
            int $i$f$label = 0;
            Actor actor$iv$iv = (Actor)(new Label(text$iv, skin$iv, style$iv));
            int $i$f$actor = 0;
            Cell var41 = (Cell)$this$image_u24default$iv.storeActor(actor$iv$iv);
            Label var40 = (Label)actor$iv$iv;
            int var38 = 0;
            this.setLabel((Label)actor$iv$iv);
        }

        float moveAmount = 5.0F;
        float duration = 0.3F;
        this.addAction((Action)Actions.forever((Action)Actions.sequence((Action)Actions.moveBy(moveAmount, 0.0F, duration), (Action)Actions.moveBy(-moveAmount, 0.0F, duration))));
    }

    @NotNull
    public final Label getLabel() {
        Label var10000 = this.label;
        if (var10000 != null) {
            return var10000;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("label");
            return null;
        }
    }

    public final void setLabel(@NotNull Label var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.label = var1;
    }

    public final void updateValues(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.getLabel().setText((CharSequence)value);
    }

    @NotNull
    public Actor cell(@NotNull Actor $this$cell, boolean grow, boolean growX, boolean growY, @Nullable Boolean expand, @Nullable Boolean expandX, @Nullable Boolean expandY, @Nullable Boolean fill, @Nullable Boolean fillX, @Nullable Boolean fillY, @Nullable Boolean uniform, @Nullable Boolean uniformX, @Nullable Boolean uniformY, @Nullable Integer align, @Nullable Integer colspan, @Nullable Float width, @Nullable Float minWidth, @Nullable Float preferredWidth, @Nullable Float maxWidth, @Nullable Float height, @Nullable Float minHeight, @Nullable Float preferredHeight, @Nullable Float maxHeight, @Nullable Float pad, @Nullable Float padTop, @Nullable Float padLeft, @Nullable Float padRight, @Nullable Float padBottom, @Nullable Float space, @Nullable Float spaceTop, @Nullable Float spaceLeft, @Nullable Float spaceRight, @Nullable Float spaceBottom, boolean row) {
        return DefaultImpls.cell(this, $this$cell, grow, growX, growY, expand, expandX, expandY, fill, fillX, fillY, uniform, uniformX, uniformY, align, colspan, width, minWidth, preferredWidth, maxWidth, height, minHeight, preferredHeight, maxHeight, pad, padTop, padLeft, padRight, padBottom, space, spaceTop, spaceLeft, spaceRight, spaceBottom, row);
    }

    @NotNull
    public Cell storeActor(@NotNull Actor actor) {
        return DefaultImpls.storeActor(this, actor);
    }

    @NotNull
    public Cell getInCell(@NotNull Actor $this$inCell) {
        return DefaultImpls.getInCell(this, $this$inCell);
    }

    // $FF: synthetic method
    // $FF: bridge method
    public Object storeActor(Actor actor) {
        return this.storeActor(actor);
    }
}
