<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xhtml="http://www.w3.org/1999/xhtml">
	<xsl:template match="/xhtml:html">
		<html>
			<xsl:apply-templates/>
		</html>
	</xsl:template>
	<xsl:template match="node()|@*" priority="-1">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*"/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="xhtml:body">
		<body bgcolor="white">
			<table>
				<tr>
					<td colspan="2" bgcolor="navy">
						<div style="color:white; font-size:larger; font-weight:bold;">
                    Processed By Cocoon 2.1.4 via Struts-Cocoon Plugin
                </div>
					</td>
				</tr>
				<tr>
					<td width="100" valign="top" bgcolor="tan">
                    Menu here
                </td>
					<td valign="top" bgcolor="olive">
						<xsl:apply-templates/>
					</td>
				</tr>
			</table>
			<i>Processed by Cocoon 2.1.4 via Struts-Cocoon Plugin 0.2.1.</i>
		</body>
	</xsl:template>
	<xsl:template match="xhtml:script">
		<xsl:choose>
			<xsl:when test="@src">
				<script>
					<xsl:attribute name="language"><xsl:value-of select="@language"/></xsl:attribute>
					<xsl:attribute name="src"><xsl:value-of select="@src"/></xsl:attribute>
					<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
					<xsl:text>
					</xsl:text>
				</script>
			</xsl:when>
			<xsl:otherwise>
				<script language="javascript" type="text/javascript">
					<xsl:comment>
						<xsl:value-of select="."/>
						<xsl:text>
						</xsl:text>
					</xsl:comment>
				</script>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
