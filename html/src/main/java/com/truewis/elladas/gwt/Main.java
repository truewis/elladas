package com.truewis.elladas.gwt;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rafaskoberg.gdx.typinglabel.TypingLabel;
import java.io.File;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.truewis.elladas.MusicManager;
import com.truewis.elladas.Separator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import ktx.scene2d.Scene2DSkin;
import org.jetbrains.annotations.NotNull;

@Metadata(
    mv = {2, 1, 0},
    k = 1,
    xi = 48,
    d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 02\u00020\u0001:\u00010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010#\u001a\u00020$J\b\u0010%\u001a\u00020$H\u0016J\u0016\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001aJ\u000e\u0010*\u001a\u00020$2\u0006\u0010)\u001a\u00020\u001aJ\b\u0010+\u001a\u00020$H\u0016J\u0018\u0010,\u001a\u00020$2\u0006\u0010-\u001a\u00020\u001b2\u0006\u0010.\u001a\u00020\u001bH\u0016J\b\u0010/\u001a\u00020$H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R-\u0010\u0018\u001a\u001e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019j\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b`\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00061"},
    d2 = {"Lcom/truewis/elladas/Main;", "Lcom/badlogic/gdx/ApplicationAdapter;", "<init>", "()V", "stage", "Lcom/badlogic/gdx/scenes/scene2d/Stage;", "skin", "Lcom/badlogic/gdx/scenes/scene2d/ui/Skin;", "statusBar", "LTopStatusBar;", "separator", "Lcom/truewis/elladas/Separator;", "window", "Lcom/badlogic/gdx/scenes/scene2d/ui/Window;", "endingDescription", "Lcom/rafaskoberg/gdx/typinglabel/TypingLabel;", "endingImage", "Lcom/badlogic/gdx/scenes/scene2d/ui/Image;", "musicManager", "Lcom/truewis/elladas/MusicManager;", "getMusicManager", "()Lcom/truewis/elladas/MusicManager;", "setMusicManager", "(Lcom/truewis/elladas/MusicManager;)V", "gState", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "getGState", "()Ljava/util/HashMap;", "assetManager", "Lcom/badlogic/gdx/assets/AssetManager;", "getAssetManager", "()Lcom/badlogic/gdx/assets/AssetManager;", "startAgain", "", "create", "func", "s", "LCardActorState;", "key", "ending", "render", "resize", "width", "height", "dispose", "Companion", "Sources of elladas.core.main"}
)
@SourceDebugExtension({"SMAP\nMain.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Main.kt\ncom/truewis/elladas/Main\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,289:1\n216#2,2:290\n216#2,2:292\n*S KotlinDebug\n*F\n+ 1 Main.kt\ncom/truewis/elladas/Main\n*L\n101#1:290,2\n105#1:292,2\n*E\n"})
public final class Main extends ApplicationAdapter {
    @NotNull
    public static final com.truewis.elladas.Main.Companion Companion;
    private Stage stage;
    private Skin skin;
    private TopStatusBar statusBar;
    private Separator separator;
    private Window window;
    private TypingLabel endingDescription;
    private Image endingImage;
    public MusicManager musicManager;
    @NotNull
    private final HashMap gState;
    @NotNull
    private final AssetManager assetManager;
    @NotNull
    private static final HashSet exhaustedKeys;
    @NotNull
    private static final JsonObject storyJson;
    @NotNull
    private static final JsonObject endingJson;
    @NotNull
    private static final List endingKeys;
    public static Sound flipSound;
    public static com.truewis.elladas.Main instance;

    public Main() {
        Pair[] var1 = new Pair[]{TuplesKt.to("religion", 30), TuplesKt.to("antiquity", 30), TuplesKt.to("economy", 30), TuplesKt.to("liberalism", 30), TuplesKt.to("time", 0)};
        this.gState = MapsKt.hashMapOf(var1);
        this.assetManager = new AssetManager();
    }

    @NotNull
    public final MusicManager getMusicManager() {
        MusicManager var10000 = this.musicManager;
        if (var10000 != null) {
            return var10000;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("musicManager");
            return null;
        }
    }

    public final void setMusicManager(@NotNull MusicManager var1) {
        Intrinsics.checkNotNullParameter(var1, "<set-?>");
        this.musicManager = var1;
    }

    @NotNull
    public final HashMap getGState() {
        return this.gState;
    }

    @NotNull
    public final AssetManager getAssetManager() {
        return this.assetManager;
    }

    public final void startAgain() {
        ((Map)this.gState).put("religion", 30);
        ((Map)this.gState).put("antiquity", 30);
        ((Map)this.gState).put("economy", 30);
        ((Map)this.gState).put("liberalism", 30);
        ((Map)this.gState).put("time", 0);
        exhaustedKeys.clear();
        this.getMusicManager().playMusic("main");
        CardActor var10000 = new CardActor;
        Stage var10002 = this.stage;
        if (var10002 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var10002 = null;
        }

        Skin var10003 = this.skin;
        if (var10003 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skin");
            var10003 = null;
        }

        Function2[] var2 = new Function2[]{new Function2(this) {
            public final void invoke(CardActorState p0, String p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                ((com.truewis.elladas.Main)this.receiver).func(p0, p1);
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object invoke(Object p1, Object p2) {
                this.invoke((CardActorState)p1, (String)p2);
                return Unit.INSTANCE;
            }
        }};
        var10000.<init>(var10002, var10003, "tutorial", CollectionsKt.arrayListOf(var2), this.gState);
        CardActor card = var10000;
        Stage var3 = this.stage;
        if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var3 = null;
        }

        var3.addActor((Actor)card);
        TopStatusBar var4 = this.statusBar;
        if (var4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statusBar");
            var4 = null;
        }

        var4.updateValues(this.gState);
        Separator var5 = this.separator;
        if (var5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("separator");
            var5 = null;
        }

        var5.setVis(false, false);
        Window var6 = this.window;
        if (var6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var6 = null;
        }

        var6.setVisible(false);
    }

    public void create() {
        Companion.setInstance(this);
        this.stage = new Stage((Viewport)(new FitViewport(1280.0F, 960.0F)));
        this.setMusicManager(new MusicManager());
        Companion.setFlipSound(Gdx.audio.newSound(Gdx.files.internal("music/flip.wav")));
        this.getMusicManager().loadMusic("lowAncient", "music/lowAncient.mp3");
        this.getMusicManager().loadMusic("lowEconomy", "music/lowEconomy.mp3");
        this.getMusicManager().loadMusic("lowLiberalism", "music/lowLiberalism.mp3");
        this.getMusicManager().loadMusic("main", "music/main.mp3");
        this.getMusicManager().loadMusic("religion", "music/religion.mp3");
        this.getMusicManager().playMusic("main");
        InternalFileHandleResolver resolver = new InternalFileHandleResolver();
        this.assetManager.setLoader(Texture.class, (AssetLoader)(new TextureLoader((FileHandleResolver)resolver)));
        this.assetManager.load("image/desk.jpg", Texture.class);
        this.assetManager.load("image/paper.jpg", Texture.class);
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("byzantine.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        BitmapFont nanum = gen.generateFont(parameter);
        ObjectMap fontMap = new ObjectMap();
        fontMap.put("byzantine", nanum);
        gen.dispose();
        SkinLoader.SkinParameter param = new SkinLoader.SkinParameter(fontMap);
        this.assetManager.load("ui2.json", Skin.class, (AssetLoaderParameters)param);
        this.assetManager.finishLoading();
        Map $this$forEach$iv = (Map)storyJson;
        int $i$f$forEach = 0;

        for(Map.Entry element$iv : $this$forEach$iv.entrySet()) {
            int var12 = 0;
            AssetManager var10000 = this.assetManager;
            Object var10001 = JsonElementKt.getJsonObject((JsonElement)element$iv.getValue()).get("image");
            Intrinsics.checkNotNull(var10001);
            var10000.load(JsonElementKt.getJsonPrimitive((JsonElement)var10001).getContent(), Texture.class);
        }

        $this$forEach$iv = (Map)endingJson;
        $i$f$forEach = 0;

        for(Map.Entry element$iv : $this$forEach$iv.entrySet()) {
            int var23 = 0;
            AssetManager var24 = this.assetManager;
            Object var45 = JsonElementKt.getJsonObject((JsonElement)element$iv.getValue()).get("image");
            Intrinsics.checkNotNull(var45);
            var24.load(JsonElementKt.getJsonPrimitive((JsonElement)var45).getContent(), Texture.class);
        }

        this.skin = (Skin)this.assetManager.get("ui2.json");
        Stage var25 = this.stage;
        if (var25 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var25 = null;
        }

        Image button = new Image((Drawable)(new TextureRegionDrawable((Texture)this.assetManager.get("image/desk.jpg", Texture.class))));
        Stage var13 = var25;
        int var20 = 0;
        button.setFillParent(true);
        var13.addActor((Actor)button);
        Window var46 = new Window;
        Skin var10004 = this.skin;
        if (var10004 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skin");
            var10004 = null;
        }

        var46.<init>("Game Over", var10004, "default");
        this.window = var46;
        TypingLabel var47 = new TypingLabel;
        CharSequence var10003 = (CharSequence)"";
        var10004 = this.skin;
        if (var10004 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skin");
            var10004 = null;
        }

        var47.<init>(var10003, var10004);
        this.endingDescription = var47;
        TypingLabel var26 = this.endingDescription;
        if (var26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endingDescription");
            var26 = null;
        }

        var26.setWrap(true);
        this.endingImage = new Image();
        Window var27 = this.window;
        if (var27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var27 = null;
        }

        var27.defaults().pad(4.0F);
        var27 = this.window;
        if (var27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var27 = null;
        }

        Image var48 = this.endingImage;
        if (var48 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endingImage");
            var48 = null;
        }

        var27.add((Actor)var48).grow().row();
        var27 = this.window;
        if (var27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var27 = null;
        }

        TypingLabel var49 = this.endingDescription;
        if (var49 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endingDescription");
            var49 = null;
        }

        var27.add((Actor)var49).grow().row();
        TextButton var30 = new TextButton;
        Skin var62 = this.skin;
        if (var62 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skin");
            var62 = null;
        }

        var30.<init>("New Game", var62);
        TextButton button = var30;
        button.pad(8.0F);
        button.addListener((EventListener)(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                com.truewis.elladas.Main.this.startAgain();
            }
        }));
        Window var31 = this.window;
        if (var31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var31 = null;
        }

        var31.add((Actor)button);
        var31 = this.window;
        if (var31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var31 = null;
        }

        var31.pack();
        var31 = this.window;
        if (var31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var31 = null;
        }

        var31.setFillParent(true);
        var31 = this.window;
        if (var31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var31 = null;
        }

        Stage var50 = this.stage;
        if (var50 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var50 = null;
        }

        float var51 = var50.getWidth() / 2.0F;
        Window var10002 = this.window;
        if (var10002 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var10002 = null;
        }

        var51 = (float)MathUtils.roundPositive(var51 - var10002.getWidth() / 2.0F);
        Stage var59 = this.stage;
        if (var59 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var59 = null;
        }

        float var60 = var59.getHeight() / 2.0F;
        Window var63 = this.window;
        if (var63 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var63 = null;
        }

        var31.setPosition(var51, (float)MathUtils.roundPositive(var60 - var63.getHeight() / 2.0F));
        var31 = this.window;
        if (var31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var31 = null;
        }

        var31.addAction((Action)Actions.sequence((Action)Actions.alpha(0.0F), (Action)Actions.fadeIn(1.0F)));
        var31 = this.window;
        if (var31 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var31 = null;
        }

        var31.setVisible(false);
        Scene2DSkin var37 = Scene2DSkin.INSTANCE;
        Skin var53 = this.skin;
        if (var53 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skin");
            var53 = null;
        }

        var37.setDefaultSkin(var53);
        TopStatusBar var54 = new TopStatusBar;
        Skin var64 = this.skin;
        if (var64 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skin");
            var64 = null;
        }

        var54.<init>(var64);
        this.statusBar = var54;
        Stage var38 = this.stage;
        if (var38 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var38 = null;
        }

        var54 = this.statusBar;
        if (var54 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("statusBar");
            var54 = null;
        }

        var38.addActor((Actor)var54);
        this.separator = new Separator();
        var38 = this.stage;
        if (var38 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var38 = null;
        }

        Separator var56 = this.separator;
        if (var56 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("separator");
            var56 = null;
        }

        var38.addActor((Actor)var56);
        Separator var40 = this.separator;
        if (var40 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("separator");
            var40 = null;
        }

        var40.setVis(false, false);
        CardActor var41 = new CardActor;
        Stage var61 = this.stage;
        if (var61 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var61 = null;
        }

        var64 = this.skin;
        if (var64 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skin");
            var64 = null;
        }

        Function2[] var21 = new Function2[]{new Function2(this) {
            public final void invoke(CardActorState p0, String p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                ((com.truewis.elladas.Main)this.receiver).func(p0, p1);
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object invoke(Object p1, Object p2) {
                this.invoke((CardActorState)p1, (String)p2);
                return Unit.INSTANCE;
            }
        }};
        var41.<init>(var61, var64, "tutorial", CollectionsKt.arrayListOf(var21), this.gState);
        CardActor card = var41;
        Stage var42 = this.stage;
        if (var42 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var42 = null;
        }

        var42.addActor((Actor)card);
        var42 = this.stage;
        if (var42 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var42 = null;
        }

        Window var57 = this.window;
        if (var57 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var57 = null;
        }

        var42.addActor((Actor)var57);
        Input var44 = Gdx.input;
        Stage var58 = this.stage;
        if (var58 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var58 = null;
        }

        var44.setInputProcessor((InputProcessor)var58);
    }

    public final void func(@NotNull CardActorState s, @NotNull String key) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(key, "key");
        if (!endingKeys.contains(key)) {
            Integer[] var4;
            int var53;
            label188: {
                var4 = new Integer[4];
                Object var10002 = storyJson.get(key);
                Intrinsics.checkNotNull(var10002);
                var10002 = JsonElementKt.getJsonObject((JsonElement)var10002).get("yesDelta");
                Intrinsics.checkNotNull(var10002);
                JsonElement var51 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var10002).get("religion");
                if (var51 != null) {
                    JsonPrimitive var52 = JsonElementKt.getJsonPrimitive(var51);
                    if (var52 != null) {
                        var53 = JsonElementKt.getInt(var52);
                        break label188;
                    }
                }

                var53 = 0;
            }

            label183: {
                var4[0] = var53;
                Object var54 = storyJson.get(key);
                Intrinsics.checkNotNull(var54);
                var54 = JsonElementKt.getJsonObject((JsonElement)var54).get("yesDelta");
                Intrinsics.checkNotNull(var54);
                JsonElement var56 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var54).get("antiquity");
                if (var56 != null) {
                    JsonPrimitive var57 = JsonElementKt.getJsonPrimitive(var56);
                    if (var57 != null) {
                        var53 = JsonElementKt.getInt(var57);
                        break label183;
                    }
                }

                var53 = 0;
            }

            label178: {
                var4[1] = var53;
                Object var59 = storyJson.get(key);
                Intrinsics.checkNotNull(var59);
                var59 = JsonElementKt.getJsonObject((JsonElement)var59).get("yesDelta");
                Intrinsics.checkNotNull(var59);
                JsonElement var61 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var59).get("economy");
                if (var61 != null) {
                    JsonPrimitive var62 = JsonElementKt.getJsonPrimitive(var61);
                    if (var62 != null) {
                        var53 = JsonElementKt.getInt(var62);
                        break label178;
                    }
                }

                var53 = 0;
            }

            label173: {
                var4[2] = var53;
                Object var64 = storyJson.get(key);
                Intrinsics.checkNotNull(var64);
                var64 = JsonElementKt.getJsonObject((JsonElement)var64).get("yesDelta");
                Intrinsics.checkNotNull(var64);
                JsonElement var66 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var64).get("liberalism");
                if (var66 != null) {
                    JsonPrimitive var67 = JsonElementKt.getJsonPrimitive(var66);
                    if (var67 != null) {
                        var53 = JsonElementKt.getInt(var67);
                        break label173;
                    }
                }

                var53 = 0;
            }

            List yesNumbers;
            Integer[] var5;
            label168: {
                var4[3] = var53;
                yesNumbers = CollectionsKt.listOf(var4);
                var5 = new Integer[4];
                Object var69 = storyJson.get(key);
                Intrinsics.checkNotNull(var69);
                var69 = JsonElementKt.getJsonObject((JsonElement)var69).get("noDelta");
                Intrinsics.checkNotNull(var69);
                JsonElement var71 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var69).get("religion");
                if (var71 != null) {
                    JsonPrimitive var72 = JsonElementKt.getJsonPrimitive(var71);
                    if (var72 != null) {
                        var53 = JsonElementKt.getInt(var72);
                        break label168;
                    }
                }

                var53 = 0;
            }

            label163: {
                var5[0] = var53;
                Object var74 = storyJson.get(key);
                Intrinsics.checkNotNull(var74);
                var74 = JsonElementKt.getJsonObject((JsonElement)var74).get("noDelta");
                Intrinsics.checkNotNull(var74);
                JsonElement var76 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var74).get("antiquity");
                if (var76 != null) {
                    JsonPrimitive var77 = JsonElementKt.getJsonPrimitive(var76);
                    if (var77 != null) {
                        var53 = JsonElementKt.getInt(var77);
                        break label163;
                    }
                }

                var53 = 0;
            }

            label158: {
                var5[1] = var53;
                Object var79 = storyJson.get(key);
                Intrinsics.checkNotNull(var79);
                var79 = JsonElementKt.getJsonObject((JsonElement)var79).get("noDelta");
                Intrinsics.checkNotNull(var79);
                JsonElement var81 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var79).get("economy");
                if (var81 != null) {
                    JsonPrimitive var82 = JsonElementKt.getJsonPrimitive(var81);
                    if (var82 != null) {
                        var53 = JsonElementKt.getInt(var82);
                        break label158;
                    }
                }

                var53 = 0;
            }

            label153: {
                var5[2] = var53;
                Object var84 = storyJson.get(key);
                Intrinsics.checkNotNull(var84);
                var84 = JsonElementKt.getJsonObject((JsonElement)var84).get("noDelta");
                Intrinsics.checkNotNull(var84);
                JsonElement var86 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var84).get("liberalism");
                if (var86 != null) {
                    JsonPrimitive var87 = JsonElementKt.getJsonPrimitive(var86);
                    if (var87 != null) {
                        var53 = JsonElementKt.getInt(var87);
                        break label153;
                    }
                }

                var53 = 0;
            }

            String var15;
            label148: {
                var5[3] = var53;
                noNumbers = CollectionsKt.listOf(var5);
                Object var10000 = storyJson.get(key);
                Intrinsics.checkNotNull(var10000);
                JsonElement var13 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var10000).get("yes");
                if (var13 != null) {
                    JsonPrimitive var14 = JsonElementKt.getJsonPrimitive(var13);
                    if (var14 != null) {
                        var15 = var14.getContent();
                        if (var15 != null) {
                            break label148;
                        }
                    }
                }

                var15 = "YES";
            }

            label142: {
                rightText = var15;
                Object var16 = storyJson.get(key);
                Intrinsics.checkNotNull(var16);
                JsonElement var17 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var16).get("no");
                if (var17 != null) {
                    JsonPrimitive var18 = JsonElementKt.getJsonPrimitive(var17);
                    if (var18 != null) {
                        var15 = var18.getContent();
                        if (var15 != null) {
                            break label142;
                        }
                    }
                }

                var15 = "NO";
            }

            String leftText = var15;
            switch (com.truewis.elladas.Main.WhenMappings.$EnumSwitchMapping$0[s.ordinal()]) {
                case 1:
                    Map var32 = (Map)this.gState;
                    Object var93 = this.gState.get("religion");
                    Intrinsics.checkNotNull(var93);
                    var32.put("religion", ((Number)var93).intValue() + ((Number)yesNumbers.get(0)).intValue());
                    var32 = (Map)this.gState;
                    var93 = this.gState.get("antiquity");
                    Intrinsics.checkNotNull(var93);
                    var32.put("antiquity", ((Number)var93).intValue() + ((Number)yesNumbers.get(1)).intValue());
                    var32 = (Map)this.gState;
                    var93 = this.gState.get("economy");
                    Intrinsics.checkNotNull(var93);
                    var32.put("economy", ((Number)var93).intValue() + ((Number)yesNumbers.get(2)).intValue());
                    var32 = (Map)this.gState;
                    var93 = this.gState.get("liberalism");
                    Intrinsics.checkNotNull(var93);
                    var32.put("liberalism", ((Number)var93).intValue() + ((Number)yesNumbers.get(3)).intValue());
                    TopStatusBar var36 = this.statusBar;
                    if (var36 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("statusBar");
                        var36 = null;
                    }

                    var36.updateValues(this.gState);
                    break;
                case 2:
                    TopStatusBar var30 = this.statusBar;
                    if (var30 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("statusBar");
                        var30 = null;
                    }

                    var30.previewValues(yesNumbers);
                    Separator var31 = this.separator;
                    if (var31 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("separator");
                        var31 = null;
                    }

                    var31.setVis(true, false);
                    break;
                case 3:
                    TopStatusBar var27 = this.statusBar;
                    if (var27 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("statusBar");
                        var27 = null;
                    }

                    Integer[] var8 = new Integer[]{0, 0, 0, 0};
                    var27.previewValues(CollectionsKt.listOf(var8));
                    Separator var28 = this.separator;
                    if (var28 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("separator");
                        var28 = null;
                    }

                    var28.setTexts(rightText, leftText);
                    var28 = this.separator;
                    if (var28 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("separator");
                        var28 = null;
                    }

                    var28.setVis(false, false);
                    break;
                case 4:
                    TopStatusBar var25 = this.statusBar;
                    if (var25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("statusBar");
                        var25 = null;
                    }

                    var25.previewValues(noNumbers);
                    Separator var26 = this.separator;
                    if (var26 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("separator");
                        var26 = null;
                    }

                    var26.setVis(false, true);
                    break;
                case 5:
                    Map var20 = (Map)this.gState;
                    Object var89 = this.gState.get("religion");
                    Intrinsics.checkNotNull(var89);
                    var20.put("religion", ((Number)var89).intValue() + ((Number)noNumbers.get(0)).intValue());
                    var20 = (Map)this.gState;
                    var89 = this.gState.get("antiquity");
                    Intrinsics.checkNotNull(var89);
                    var20.put("antiquity", ((Number)var89).intValue() + ((Number)noNumbers.get(1)).intValue());
                    var20 = (Map)this.gState;
                    var89 = this.gState.get("economy");
                    Intrinsics.checkNotNull(var89);
                    var20.put("economy", ((Number)var89).intValue() + ((Number)noNumbers.get(2)).intValue());
                    var20 = (Map)this.gState;
                    var89 = this.gState.get("liberalism");
                    Intrinsics.checkNotNull(var89);
                    var20.put("liberalism", ((Number)var89).intValue() + ((Number)noNumbers.get(3)).intValue());
                    TopStatusBar var24 = this.statusBar;
                    if (var24 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("statusBar");
                        var24 = null;
                    }

                    var24.updateValues(this.gState);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } else {
            String var40;
            label136: {
                Object var37 = endingJson.get(key);
                Intrinsics.checkNotNull(var37);
                JsonElement var38 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var37).get("answer");
                if (var38 != null) {
                    JsonPrimitive var39 = JsonElementKt.getJsonPrimitive(var38);
                    if (var39 != null) {
                        var40 = var39.getContent();
                        if (var40 != null) {
                            break label136;
                        }
                    }
                }

                var40 = "...";
            }

            String rightText;
            label130: {
                rightText = var40;
                Object var41 = endingJson.get(key);
                Intrinsics.checkNotNull(var41);
                JsonElement var42 = (JsonElement)JsonElementKt.getJsonObject((JsonElement)var41).get("answer");
                if (var42 != null) {
                    JsonPrimitive var43 = JsonElementKt.getJsonPrimitive(var42);
                    if (var43 != null) {
                        var40 = var43.getContent();
                        if (var40 != null) {
                            break label130;
                        }
                    }
                }

                var40 = "...";
            }

            String leftText = var40;
            switch (com.truewis.elladas.Main.WhenMappings.$EnumSwitchMapping$0[s.ordinal()]) {
                case 1:
                    this.ending(key);
                    break;
                case 2:
                    Separator var49 = this.separator;
                    if (var49 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("separator");
                        var49 = null;
                    }

                    var49.setVis(true, false);
                    break;
                case 3:
                    TopStatusBar var46 = this.statusBar;
                    if (var46 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("statusBar");
                        var46 = null;
                    }

                    Integer[] var7 = new Integer[]{0, 0, 0, 0};
                    var46.previewValues(CollectionsKt.listOf(var7));
                    Separator var47 = this.separator;
                    if (var47 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("separator");
                        var47 = null;
                    }

                    var47.setTexts(rightText, leftText);
                    var47 = this.separator;
                    if (var47 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("separator");
                        var47 = null;
                    }

                    var47.setVis(false, false);
                    break;
                case 4:
                    Separator var45 = this.separator;
                    if (var45 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("separator");
                        var45 = null;
                    }

                    var45.setVis(false, true);
                    break;
                case 5:
                    this.ending(key);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

    }

    public final void ending(@NotNull String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Image var10000 = this.endingImage;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endingImage");
            var10000 = null;
        }

        AssetManager var10003 = this.assetManager;
        Object var10004 = endingJson.get(key);
        Intrinsics.checkNotNull(var10004);
        var10004 = JsonElementKt.getJsonObject((JsonElement)var10004).get("image");
        Intrinsics.checkNotNull(var10004);
        Object var6 = var10003.get(JsonElementKt.getJsonPrimitive((JsonElement)var10004).getContent(), Texture.class);
        Intrinsics.checkNotNull(var6);
        var10000.setDrawable((Drawable)(new TextureRegionDrawable((Texture)var6)));
        TypingLabel var2 = this.endingDescription;
        if (var2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endingDescription");
            var2 = null;
        }

        Object var10001 = endingJson.get(key);
        Intrinsics.checkNotNull(var10001);
        var10001 = JsonElementKt.getJsonObject((JsonElement)var10001).get("ending");
        Intrinsics.checkNotNull(var10001);
        var2.restart((CharSequence)JsonElementKt.getJsonPrimitive((JsonElement)var10001).getContent());
        Window var3 = this.window;
        if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var3 = null;
        }

        var3.layout();
        var3 = this.window;
        if (var3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("window");
            var3 = null;
        }

        var3.setVisible(true);
    }

    public void render() {
        ScreenUtils.clear(0.0F, 0.0F, 0.0F, 1.0F);
        Stage var10000 = this.stage;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var10000 = null;
        }

        var10000.act(Gdx.graphics.getDeltaTime());
        var10000 = this.stage;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var10000 = null;
        }

        var10000.draw();
    }

    public void resize(int width, int height) {
        Stage var10000 = this.stage;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var10000 = null;
        }

        var10000.getViewport().update(width, height);
    }

    public void dispose() {
        Stage var10000 = this.stage;
        if (var10000 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stage");
            var10000 = null;
        }

        var10000.dispose();
        Skin var1 = this.skin;
        if (var1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skin");
            var1 = null;
        }

        var1.dispose();
    }

    static {
        String var3;
        Json.Default var10000;
        label23: {
            Companion = new com.truewis.elladas.Main.Companion((DefaultConstructorMarker)null);
            exhaustedKeys = new HashSet();
            var10000 = Json.Default;
            Files var10001 = Gdx.files;
            if (var10001 != null) {
                FileHandle var2 = var10001.internal("story.json");
                if (var2 != null) {
                    var3 = var2.readString();
                    if (var3 != null) {
                        break label23;
                    }
                }
            }

            var3 = FilesKt.readText$default(new File("../assets/story.json"), (Charset)null, 1, (Object)null);
        }

        label17: {
            storyJson = JsonElementKt.getJsonObject(var10000.parseToJsonElement(var3));
            var10000 = Json.Default;
            Files var4 = Gdx.files;
            if (var4 != null) {
                FileHandle var5 = var4.internal("endings.json");
                if (var5 != null) {
                    var3 = var5.readString();
                    if (var3 != null) {
                        break label17;
                    }
                }
            }

            var3 = FilesKt.readText$default(new File("../assets/endings.json"), (Charset)null, 1, (Object)null);
        }

        endingJson = JsonElementKt.getJsonObject(var10000.parseToJsonElement(var3));
        String[] var0 = new String[]{"religion", "lowReligion", "lowEconomy", "lowAntiquity", "mundane", "lowLiberalism"};
        endingKeys = CollectionsKt.listOf(var0);
    }

    @Metadata(
        mv = {2, 1, 0},
        k = 1,
        xi = 48,
        d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006 "},
        d2 = {"Lcom/truewis/elladas/Main$Companion;", "", "<init>", "()V", "exhaustedKeys", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getExhaustedKeys", "()Ljava/util/HashSet;", "storyJson", "Lkotlinx/serialization/json/JsonObject;", "getStoryJson", "()Lkotlinx/serialization/json/JsonObject;", "endingJson", "getEndingJson", "endingKeys", "", "getEndingKeys", "()Ljava/util/List;", "flipSound", "Lcom/badlogic/gdx/audio/Sound;", "getFlipSound", "()Lcom/badlogic/gdx/audio/Sound;", "setFlipSound", "(Lcom/badlogic/gdx/audio/Sound;)V", "instance", "Lcom/truewis/elladas/Main;", "getInstance", "()Lcom/truewis/elladas/Main;", "setInstance", "(Lcom/truewis/elladas/Main;)V", "Sources of elladas.core.main"}
    )
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final HashSet getExhaustedKeys() {
            return com.truewis.elladas.Main.exhaustedKeys;
        }

        @NotNull
        public final JsonObject getStoryJson() {
            return com.truewis.elladas.Main.storyJson;
        }

        @NotNull
        public final JsonObject getEndingJson() {
            return com.truewis.elladas.Main.endingJson;
        }

        @NotNull
        public final List getEndingKeys() {
            return com.truewis.elladas.Main.endingKeys;
        }

        @NotNull
        public final Sound getFlipSound() {
            Sound var10000 = com.truewis.elladas.Main.flipSound;
            if (var10000 != null) {
                return var10000;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("flipSound");
                return null;
            }
        }

        public final void setFlipSound(@NotNull Sound var1) {
            Intrinsics.checkNotNullParameter(var1, "<set-?>");
            com.truewis.elladas.Main.flipSound = var1;
        }

        @NotNull
        public final com.truewis.elladas.Main getInstance() {
            com.truewis.elladas.Main var10000 = com.truewis.elladas.Main.instance;
            if (var10000 != null) {
                return var10000;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("instance");
                return null;
            }
        }

        public final void setInstance(@NotNull com.truewis.elladas.Main var1) {
            Intrinsics.checkNotNullParameter(var1, "<set-?>");
            com.truewis.elladas.Main.instance = var1;
        }

        // $FF: synthetic method
        public Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }

    // $FF: synthetic class
    @Metadata(
        mv = {2, 1, 0},
        k = 3,
        xi = 48
    )
    public static final class WhenMappings {
        // $FF: synthetic field
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] var0 = new int[CardActorState.values().length];

            try {
                var0[CardActorState.YES.ordinal()] = 1;
            } catch (NoSuchFieldError var6) {
            }

            try {
                var0[CardActorState.YES_TILT.ordinal()] = 2;
            } catch (NoSuchFieldError var5) {
            }

            try {
                var0[CardActorState.NEUTRAL.ordinal()] = 3;
            } catch (NoSuchFieldError var4) {
            }

            try {
                var0[CardActorState.NO_TILT.ordinal()] = 4;
            } catch (NoSuchFieldError var3) {
            }

            try {
                var0[CardActorState.NO.ordinal()] = 5;
            } catch (NoSuchFieldError var2) {
            }

            $EnumSwitchMapping$0 = var0;
        }
    }
}
