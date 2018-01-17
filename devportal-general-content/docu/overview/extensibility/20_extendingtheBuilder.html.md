---
type: Extensibility
title: 'Extending the Builder'
---

UI extensibility in the Builder is a functionality that enables developers and owners to customize the structure of separate views according to their needs. You can do this by creating widgets in `module.json` files inside your Builder module structure. The process consists of creating two different Builder modules:

* **Extensible Builder Module**: Create a template for an extensible view with a customized number and height of columns with personalized background colors by using an extensible Builder module.
* ** Extending Builder Module**: Modify the `module.json` file in the extending Builder module according to the previously created template and implement it in the Builder.

#### Create an extensible Builder module

Create a new Builder module in your root Builder folder and follow the instructions in the <a href="/tools/buildersdk/index.html#CreatingYourOwnUIModuleUsingtheBuilderSDKCLI">Builder SDK</a> documentation. Instead of an example code snippet from there, use the one shown below:
```
{
    "widgets": [{
        "id": "myExtensibleWidgetId",
        "title": "My Extensible Widget",
        "settings": {
            "viewUrl": "index.html"
        },
        "extensible" : {
            "layout" : "grid",
            "columns" : [
                "4", "4", "4"
            ],
            "height" : "dynamic",
            "background" : "#EDF1F5"
        }
    }]
}
```

The aforementioned code snippet consists of the extensible widget. Inside the widget, you can specify following properties:

<ul>
	<li>**layout** - The type of layout in the specific view. For now, you can make a grid out of your view in a Builder module.</li>
	<li>**columns** - Specify the width of separate columns as well as their number. Total width is limited to 12.</li>
	<li>**height** - Set the height as dynamic or specify the size, such as "300px".</li>
	<li>**background** - Set the background color here by adding the color name or its RGB code.</li>
</ul>

In order to implement your extensible Builder module in the Builder, follow Steps 2 and 3 from the <a href="/gettingstarted/createabuildermodule/index.html">Create a Builder Module</a> section of the Getting Started guides.

You have now implemented the framework for the extensible Builder module. The next section describes how to make an extensible Builder module visible in the Builder.

#### Extend your Builder module

Create another Builder module in your root Builder folder where you can design a new extensible view in the Builder. The code snippet for the newly created `module.json` file is shown below:
```
{
    "widgets": [{
        "id": "myWidgetId",
        "title": "My Widget",
        "settings": {
            "viewUrl": "index.html"
        },
        "extends": {
            "id": "myExtensibleWidgetId"
        }
    }]
}
```

In order to extend and implement your Builder module in the Builder, perform the same steps as in the previous section. Your Builder module will be visible in the project navigation with the name you specified in the **title** field.
