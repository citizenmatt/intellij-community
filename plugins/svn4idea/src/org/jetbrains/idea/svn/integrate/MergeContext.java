/*
 * Copyright 2000-2014 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.idea.svn.integrate;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.idea.svn.SvnVcs;
import org.jetbrains.idea.svn.dialogs.WCInfo;
import org.tmatesoft.svn.core.SVNURL;

import static org.jetbrains.idea.svn.SvnUtil.ensureStartSlash;
import static org.jetbrains.idea.svn.SvnUtil.getRelativeUrl;

/**
 * @author Konstantin Kolosovsky.
 */
public class MergeContext {

  @NotNull private final Project myProject;
  @NotNull private final String myBranchName;
  @NotNull private final VirtualFile myRoot;
  @NotNull private final WCInfo myWcInfo;
  @NotNull private final SVNURL mySourceUrl;
  @NotNull private final SvnVcs myVcs;
  @NotNull private final String myTitle;
  @NotNull private final String myRepositoryRelativeSourcePath;
  @NotNull private final String myRepositoryRelativeWorkingCopyPath;

  public MergeContext(@NotNull SvnVcs vcs,
                      @NotNull SVNURL sourceUrl,
                      @NotNull WCInfo wcInfo,
                      @NotNull String branchName,
                      @NotNull VirtualFile root) {
    myVcs = vcs;
    myProject = vcs.getProject();
    myBranchName = branchName;
    myRoot = root;
    mySourceUrl = sourceUrl;
    myWcInfo = wcInfo;
    myTitle = "Merge from " + myBranchName;
    myRepositoryRelativeSourcePath = ensureStartSlash(getRelativeUrl(myWcInfo.getRepoUrl(), mySourceUrl));
    myRepositoryRelativeWorkingCopyPath = ensureStartSlash(getRelativeUrl(myWcInfo.getRepoUrl(), myWcInfo.getUrl()));
  }

  @NotNull
  public Project getProject() {
    return myProject;
  }

  @NotNull
  public String getBranchName() {
    return myBranchName;
  }

  @NotNull
  public VirtualFile getRoot() {
    return myRoot;
  }

  @NotNull
  public WCInfo getWcInfo() {
    return myWcInfo;
  }

  @NotNull
  public SVNURL getSourceUrl() {
    return mySourceUrl;
  }

  @NotNull
  public String getRepositoryRelativeSourcePath() {
    return myRepositoryRelativeSourcePath;
  }

  @NotNull
  public String getRepositoryRelativeWorkingCopyPath() {
    return myRepositoryRelativeWorkingCopyPath;
  }

  @NotNull
  public SvnVcs getVcs() {
    return myVcs;
  }

  @NotNull
  public String getTitle() {
    return myTitle;
  }
}