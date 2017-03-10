package com.cnu.pojo;
import java.io.Serializable;
import com.cnu.annotation.Column;
public class Files implements Serializable
{
	private static final long serialVersionUID = -812417216802169203L;
	private Integer id;
	@Column("content_id")
	private Integer contentId;
	private String name;
	private String path;
	private int isvalid;
	@Column("model_id")
	private Integer modelId;
	@Column("channel_id")
	private Integer channelId;
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getContentId()
	{
		return contentId;
	}
	public void setContentId(Integer contentId)
	{
		this.contentId = contentId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public int getIsvalid()
	{
		return isvalid;
	}
	public void setIsvalid(int isvalid)
	{
		this.isvalid = isvalid;
	}
	public Integer getModelId()
	{
		return modelId;
	}
	public void setModelId(Integer modelId)
	{
		this.modelId = modelId;
	}
	public Integer getChannelId()
	{
		return channelId;
	}
	public void setChannelId(Integer channelId)
	{
		this.channelId = channelId;
	}
	
}
