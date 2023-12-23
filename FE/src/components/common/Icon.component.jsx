import styled from "styled-components";
import { MdArrowBackIosNew } from "react-icons/md";
import { BsTrash3 } from "react-icons/bs";
import { IoIosCheckboxOutline, IoIosSearch } from "react-icons/io";
import { IoCloseOutline } from "react-icons/io5";
import { HiOutlinePlus } from "react-icons/hi2";

const StyledIcon = styled.span`
  height: 20px;
  font-size: 20px;
  position: absolute;
  top: 0;
  bottom: 0;
  margin: auto;
  right: 7px;
`;

export const Icon = ({ type }) => {
  const IconType = {
    search: <IoIosSearch />,
    back: <MdArrowBackIosNew />,
    trash: <BsTrash3 />,
    checkbox: <IoIosCheckboxOutline />,
    close: <IoCloseOutline />,
    plus: <HiOutlinePlus />,
  };

  return <StyledIcon>{IconType[type]}</StyledIcon>;
};
