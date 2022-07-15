package app.revanced.integrations.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.*

/**
 * helper class for working with (injected) resources
 */
@Suppress("unused")
object ResourcesHelper {
    /**
     * resource types supported by [ResourcesHelper.getIdentifier]
     */
    enum class ResourceType(val typeName: String) {
        DRAWABLE("drawable"),
        LAYOUT("layout"),
        STRING("string"),
        COLOR("color"),
        VIEW_ID("id"),
        XML("xml"),
        DIMENSION("dimen")
    }

    /**
     * resolve a resource identifier from the resource name
     *
     * @param type the resource type to find
     * @param name the name of the resource
     * @return the resource id that was found, or null if none found
     */
    @JvmStatic
    fun Context.getIdentifier(type: ResourceType, name: String): Int =
        getIdentifier(type, name, packageName)

    /**
     * resolve a resource identifier from the resource name
     *
     * @param type the resource type to find
     * @param name the name of the resource
     * @param pkg package name to search in
     * @return the resource id that was found, or 0 if not found
     */
    @JvmStatic
    fun Context.getIdentifier(type: ResourceType, name: String, pkg: String): Int {
        val id = resources.getIdentifier(name, type.typeName, pkg)
        return if (id != 0) id else {
            Logging.error(ResourcesHelper::class.java, "Resource '$name' was not found")
            0
        }
    }

    /**
     * context used for R.* style api
     */
    private val ctx: Context
        get() = ReVancedUtils.context

    object Strings {
        /**
         * get the id of a string resource, equivalent to 'R.string.my_string'
         *
         * @param name the name of the string resource
         * @return the resource id, or 0 if not found
         */
        @JvmStatic
        @StringRes
        @JvmName("getID")
        operator fun get(name: String): Int =
            ctx.getIdentifier(ResourceType.STRING, name)

        /**
         * get a string by its id name, see [Context.getString]
         *
         * @param name the name of the string resource
         * @param formatArgs optional formatting args for use with formatted strings
         * @return the string, or a fallback if not found
         */
        @JvmStatic
        fun getString(name: String, vararg formatArgs: Any): String =
            get(name).let { if (it != 0) ctx.getString(it, formatArgs) else "[\"$name\"]" }

        /**
         * get a string by its id name, see [Context.getText]
         *
         * @param name the name of the string resource
         * @return the string, or a fallback if not found
         */
        @JvmStatic
        fun getText(name: String): CharSequence =
            get(name).let { if (it != 0) ctx.getText(it) else "[\"$name\"]" }
    }

    object Drawables {
        /**
         * get the id of a drawable resource, equivalent to 'R.drawable.my_icon'
         *
         * @param name the name of the drawable resource
         * @return the resource id, or 0 if not found
         */
        @JvmStatic
        @DrawableRes
        @JvmName("getID")
        operator fun get(name: String): Int =
            ctx.getIdentifier(ResourceType.DRAWABLE, name)

        /**
         * get value of a drawable by its id name, see [Context.getDrawable]
         *
         * @param name the name of the drawable resource
         * @return the drawable, or null if not found
         */
        @JvmStatic
        fun getDrawable(name: String): Drawable? =
            get(name).let { if (it != 0) ctx.getDrawable(it) else null }
    }

    object Colors {
        /**
         * get the id of a color resource, equivalent to 'R.color.my_color'
         *
         * @param name the name of the color resource
         * @return the resource id, or 0 if not found
         */
        @JvmStatic
        @ColorRes
        @JvmName("getID")
        operator fun get(name: String): Int =
            ctx.getIdentifier(ResourceType.COLOR, name)

        /**
         * get value of a color by its id name, see [Context.getColor]
         *
         * @param name the name of the color resource
         * @return the color value, or null if not found
         */
        @JvmStatic
        @ColorInt
        fun getColor(name: String): Int? =
            get(name).let { if (it != 0) ctx.getColor(it) else null }
    }

    object IDs {
        /**
         * get the id of a view, equivalent to 'R.id.my_view'
         *
         * @param name the name of the string resource
         * @return the resource id, or 0 if not found
         */
        @JvmStatic
        @IdRes
        @JvmName("getID")
        operator fun get(name: String): Int =
            ctx.getIdentifier(ResourceType.VIEW_ID, name)
    }

    object Layouts {
        /**
         * get the id of a layout resource, equivalent to 'R.layout.my_layout'
         *
         * @param name the name of the layout resource
         * @return the resource id, or 0 if not found
         */
        @JvmStatic
        @LayoutRes
        @JvmName("getID")
        operator fun get(name: String): Int =
            ctx.getIdentifier(ResourceType.LAYOUT, name)
    }

    object XMLs {
        /**
         * get the id of a xml resource, equivalent to 'R.xml.my_xml'
         *
         * @param name the name of the xml resource
         * @return the resource id, or 0 if not found
         */
        @JvmStatic
        @XmlRes
        @JvmName("getID")
        operator fun get(name: String): Int =
            ctx.getIdentifier(ResourceType.XML, name)
    }
}
