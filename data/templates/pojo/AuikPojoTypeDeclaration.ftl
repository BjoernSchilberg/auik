<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
/**
${pojo.getClassJavaDoc(
"A class that represents a row in a database table.
You can customize the behavior of this class by editing the class,
{@link " + pojo.getDeclarationName() + "}.", 0)}
 */
<#include "Ejb3TypeDeclaration.ftl"/>
${pojo.getClassModifiers()} abstract ${pojo.getDeclarationType()} Abstract${declarationName} ${pojo.getExtendsDeclaration()} ${pojo.getImplementsDeclaration()}