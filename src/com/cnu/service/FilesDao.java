package com.cnu.service;
import java.sql.SQLException;
import java.util.List;
import com.cnu.pojo.Files;
import com.cnu.dao.core.BaseDao;
import com.cnu.page.PageDiv;
public interface FilesDao extends BaseDao<Files>
{
    public List<Files> getFilesByChannel(Integer channelId)throws SQLException;
    public void updateFilesBychannel(Integer channelId)throws SQLException;
    /**
     * 根据模型栏目和内容来删除资源
     * @param modelId
     * @param channelId
     * @param contentId
     * @throws SQLException
     */
    public void deleteFilesByModeContentChannel(int modelId,int channelId,int contentId)throws SQLException;
    /**
     * 得到一篇文章对应的资源
     * @param modelId
     * @param channelId
     * @param contentId
     * @return
     * @throws SQLException
     */
    public List<Files> FilesByModeContentChannel(int modelId,int channelId,int contentId)throws SQLException;
    /**
     * 找出可用与不可用的资源
     * @param pageNo
     * @param pageSize
     * @param valid  0为没有使用的，可以删除，1为被使用的
     * @return
     * @throws SQLException
     */
    
    public PageDiv<Files> findFilesByValid(int pageNo,int pageSize,int valid)throws SQLException;
}
