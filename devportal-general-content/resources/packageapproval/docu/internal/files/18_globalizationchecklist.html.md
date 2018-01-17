---
title: Globalization Checklist
---

#### Translation and localization
Languages currently supported for YaaS Market:
* English (enUS)
* German (deDE)

#### Additional Terms of Use
If you have additional terms of use, these must be first checked by the appropriate market legal counsel. For more information, see the section on Additional Terms and Conditions.
The terms can then by translated by SAP Language Services (SLS).

When the terms have been legally approved, they must be translated into the official market language. Use the <a href=https://ted.wdf.sap.corp/CorporateRequest.aspx>Translation Request Form for Corporate</a>, and under *Content Type* select *Legal*.

Please plan at least one week for the translation.

#### Package Short and Long Descriptions
Ensure that your package short and long descriptions have been reviewed in English. If you need assistance here, contact <a href="mailto:veronica.o-looney@sap.com">Veronica O'Looney</a>

To get your descriptions translated you must first give SLS an idea of how much text there is so that they can plan resources accordingly.

Send an email to <a href="mailto:birgit.stolle@sap.com">Birgit Stolle</a> and <a href="mailto:kerstin.bier@sap.com">Kerstin Bier</a>, with <a href="mailto:marco.dorn@sap.com">Marco Dorn</a> on CC, telling them how many package descriptions you have, roughly how much text there is, and which languages you require.

To upload your descriptions for translation:
* Go to the mdoc area <a href="https://mdocs.sap.com/mcm/public/v1/open?shr=9sYUTStl7SIJprjwfzaEBwusKxaTtsX41u83s1qfAfU">SAP Mobile Docs - YaaS</a>.
* Download the properties file template and read the instructions there.
* Fill out the details and enter your package short and long description. Don't forget to format it correctly.
* Save the properties file using the following naming convention: Package_Name_packagedesc.properties. So, if your package is called My Profile, then the file name should be *My_Profile_packagedesc.properties*.
* Create a folder with the name of your package and your D-number (example: My_Profile_D0....) upload your properties file to this folder.
* Inform SLS that translation can start.

Again, please plan at least one week for translation, and ensure you name a contact person should SLS have questions.

#### UI Texts and Builder Modules

* A new process for getting UI texts and builder modules translated will be rolled out soon. Please check this page for updates. In the meantime, contact <a href="mailto:birgit.stolle@sap.com">Birgit Stolle</a> and <a href="mailto:kerstin.bier@sap.com">Kerstin Bier</a> if you need these translated.
* Check out other globalization requirements for your UIs and Builder modules at <a href="https://wiki.wdf.sap.corp/wiki/x/kZlIVg">Product Standard Globalization</a>.
* Provide local pricing metrics.

#### Deployments and Usage of Third-Party Software

* For package owners outside of `yaas.io` and the SAP Hybris organization, get approval from the <a href="mailto:DL_5744A46F7BCF84A4D900001E@exchange.sap.corp">DL CEC Commerce Third-party License</a> team for any third-party software used in your packages.
* For package owners of `yaas.io` and the SAP Hybris organization, get approval from <a href="mailto:phillippe.sauve@sap.com">Philippe Sauve</a> for any third-party software used in your packages.
* Deploy to the new region.</li>
* Create the API Proxy for beta and all local markets.

#### Monitoring and Support

* Ensure the on-call schedule covers the new market's hours of business.</li>
* For new regions, monitor the new regional status page.

#### Compliance with Data Protection Regulations for Germany
* Implement a mechanism to delete or anonymize personal data used in your services and packages.
* If a customer requests to remove all their personal data from YaaS, delete it or at least anonymize it automatically. Scramble the data in a irreversible way that it can't be connected anymore to the person behind the dataset. For instance, you can not identify "John Smith" ever again because his name is scrambled to "Lorem Ipsum".
* Make sure to also consider the following references:
  * Some primary keys might contain personal data.
  * Deleting items might break other services that reference them, such as customer #8210 might be referenced by the order service.
  * Packages might include a link to the YaaS account which created the package.
